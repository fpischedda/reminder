(ns reminder.api.server
  (:require [reminder.api.handlers :refer [handlers]]
            [reminder.config :refer [config]]
            [reminder.api.authorization :refer [rules]]
            [mount.core :refer [defstate]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [buddy.auth.backends :as backends]
            [buddy.auth.middleware :refer [wrap-authentication
                                           wrap-authorization]]
            [buddy.auth.accessrules :refer [wrap-access-rules]]
            [org.httpkit.server :refer [run-server]]))

(defonce server-instance (atom nil))

(def reminder-defaults (assoc site-defaults :security {:anti-forgery false}))
(defn gen-auth-backend [auth-secret]
  (backends/jws {:secret auth-secret}))

(def access-options {:rules rules})

(defn wrap-content-type-if-not-set
  [handler content-type]
  (fn [request]
    (let [response (handler request)
          content-type (:headers "Content-Type")]
      (if content-type
        response
        (assoc-in response [:headers "Content-Type"] content-type)))))

(defn allow-cross-origin
  "middleware function to allow cross origin"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (-> response
          (assoc-in [:headers "Access-Control-Allow-Origin"]  "*")
          (assoc-in [:headers "Access-Control-Allow-Methods"] "GET,PUT,POST,PATCH,DELETE,OPTIONS")
          (assoc-in [:headers "Access-Control-Allow-Headers"] "X-Requested-With,Content-Type,Cache-Control, Authorization")))))

(defn gen-app [auth-secret]
  (let [auth-backend (gen-auth-backend auth-secret)]
    (-> handlers
        (wrap-defaults reminder-defaults)
        (wrap-keyword-params)
        (wrap-json-params)
        (wrap-content-type-if-not-set "application/json")
        (wrap-access-rules access-options)
        (wrap-authorization auth-backend)
        (allow-cross-origin)
        (wrap-authentication auth-backend))))

(defn start []
  (let [{:keys [api-server auth-secret]} config]
    (println "starting api server " api-server)
    (reset! server-instance (run-server (gen-app auth-secret) api-server))))

(defn stop []
  (when-not (nil? @server-instance)
    (println "quitting api server")
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server-instance :timeout 100)
    (reset! server-instance nil)))

(defstate server
  :start (start)
  :stop (stop))
