(ns reminder.api.auth
  (:require
   [clj-time.core :as time]
   [buddy.sign.jwt :as jwt]
   [cheshire.core :as json]
   [ring.util.http-response :as response]
   [reminder.config :refer [config]]
   [reminder.data.users :as users]
   [reminder.utils :refer [datetime->str]]))

(defn get-token-claims [email expire-date]
  {:usr email
   :exp expire-date})

(defn gen-token-expire-date []
  (time/plus (time/now) (time/seconds (:session-expiration-seconds config))))

(defn login
  [{:keys [email password]}]
  (if (users/exists email password)
    (let [expire-date (gen-token-expire-date)
          claims (get-token-claims email expire-date)
          token (jwt/sign claims (:auth-secret config))]
      (response/ok {:access_token token
                    :expires (datetime->str expire-date)}))
    (response/unauthorized)))

(defn register
  [{:keys [email password]}]
  (if (users/create email password)
    (response/created {:resut :success})
    (response/bad-request [{:code :register/user-exists
                            :text "unable to register, user exists"}])))
