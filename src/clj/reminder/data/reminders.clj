(ns reminder.data.reminders
  (:require [monger.collection :as mc]
            [monger.operators :refer :all]
            [reminder.data.connection :refer [database]]
            [reminder.data.utils :refer [gen-id]]))

(defn create [sender message recipients]
  (mc/insert-and-return database "reminders" {:_id (gen-id)
                                            :sender
                                            :recipients
                                            :message message
                                            :comments []
                                            :status :created}))

(defn update-status [reminder-id status]
  (mc/update-by-id database "reminders" reminder-id {$set {:status status}}))

(defn add-comment [reminder-id commentator]
  (update-status invite-id :accepted))

(defn decline [invite-id]
  (update-status invite-id :declined))
