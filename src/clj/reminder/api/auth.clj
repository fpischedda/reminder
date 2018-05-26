(ns reminder.api.auth
  (:require
   [clj-time.core :as time]
   [buddy.sign.jwt :as jwt]
   [cheshire.core :as json]
   [reminder.config :refer [config]]
   [reminder.data.users :as users]))

(defn get-token-claims [username]
  {:username username
   :exp (time/plus (time/now) (time/seconds (:session-expiration-seconds config)))})

(defn login
  [request]
  (let [data (:params request)
        username (:username data)
        user-exists (users/exists username
                                  (:password data))
        claims (get-token-claims username)
        token (jwt/sign claims (:auth-secret config))]
    (if user-exists
      (json/encode {:token token
                    :username username})
      (json/encode {:errors [{:code :user-not-found
                              :text "user not found"}]}))))

(defn register [req]
  (let [data (:params req)
        username (:username data)
        user-id (:_id (users/create
                       username
                       (:email data)
                       (:password data)))
        claims (get-token-claims username)
        token (jwt/sign claims (:auth-secret config))]
    (if user-id
      (json/encode {:token token
                    :username username})
      (json/encode {:errors [{:code :unable-to-register
                              :text "unable to register"}]}))))
