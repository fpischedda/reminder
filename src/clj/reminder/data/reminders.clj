(ns reminder.data.reminders
  (:require [monger.collection :as mc]
            [monger.operators :refer :all]
            [reminder.data.connection :refer [database]]))

(defn create [reminder-id sender recipients message created]
  (mc/insert-and-return database "reminders" {:_id reminder-id
                                              :sender sender
                                              :recipients recipients
                                              :message message
                                              :created created
                                              :status :created}))

(defn update-status [reminder-id status]
  (mc/update-by-id database "reminders" reminder-id {$set {:status status}}))

(defn add-comment [reminder-id message commentator created]
  (let [comment {:message message
                 :commentator commentator
                 :created created}]
    (mc/update-by-id database "reminders" reminder-id
                     {$push {:comments comment}})))

(defn set-closed [reminder-id]
  (update-status reminder-id :closed))
