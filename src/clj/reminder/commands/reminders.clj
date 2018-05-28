(ns reminder.commands.reminders
  (:require [java-time :as j
             [monger.collection :as mc]
             [monger.operators :refer :all]
             [reminder.data.connection :refer [database]]
             [reminder.data.utils :refer [gen-id]]]))

(defn utc-now []
  (str (j/instant)))

(defn create [sender message recipients]
  {:_id (gen-id)
   :sender sender
   :recipients recipients
   :message message
   :comments []
   :created (utc-now)
   :status :created})

