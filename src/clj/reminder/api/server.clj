(ns reminder.api.server
  (:require [reminder.api.handlers :refer [handlers]]
            [reminder.config :refer [config]]
            [mount.core :refer [defstate]]
            [ring.middleware.json :refer [wrap-json-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [buddy.auth.backends :as backends]
            [buddy.auth.middleware :refer [wrap-authentication
                                           wrap-authorization]]
            [buddy.auth.accessrules :refer [wrap-access-rules]]
            [org.httpkit.server :refer [run-server]]))

(defn gen-auth-backend [auth-secret]
  (backends/jws {:secret auth-secret}))

(defn wrap-print-request [handler]
  (fn [request]
    (do
      (println request)
      (handler request))))

(defn gen-app [auth-secret]
  (let [auth-backend (gen-auth-backend auth-secret)]
    (-> handlers
      (wrap-print-request)
      (wrap-authorization auth-backend)
      (wrap-authentication auth-backend))))

(defonce server-instance (atom nil))

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
