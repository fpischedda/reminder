(ns reminder.data.reminders
  (:require [java-time :as j]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [reminder.data.connection :refer [database]]
            [reminder.data.utils :refer [gen-id]]))

(defn create [reminder]
  (mc/insert-and-return database "reminders" reminder))

(defn update-status [reminder-id status]
  (mc/update-by-id database "reminders" reminder-id {$set {:status status}}))

(defn add-comment [reminder-id message commentator]
  (let [comment {:message message
                 :commentator commentator
                 :created (utc-now)}]
    (mc/update-by-id database "reminders" reminder-id
                     {$push {:comments comment}})))

(defn set-closed [reminder-id]
  (update-status reminder-id :closed))
