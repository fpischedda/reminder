(ns reminder.data.users
  (:require [monger.collection :as mc]
            [buddy.hashers :as hashers]
            [reminder.data.utils :refer [paged-filter]]
            [reminder.data.connection :refer [database]]))

(defn get-hash [text]
  (hashers/derive text))

(defn all [page page-size]
  (paged-filter database "users" page page-size {}))

(defn create [email password]
  (try
    (mc/insert database "users" {:_id email
                                 :password (get-hash password)})
    (catch Exception e nil)))

(defn exists [email password]
  (let [user (mc/find-one-as-map database "users" {:_id email})]
    (hashers/check password (:password user))))
