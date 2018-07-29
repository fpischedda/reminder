(ns reminder.commands.handlers.reminders
  (:require [reminder.data.reminders :as reminders]
            [reminder.data.utils :refer [gen-id]]
            [reminder.utils :refer [utc-now]]))

(defn create-handler [_ payload]
  (let [{:keys [sender recipients message]} payload
        id (gen-id)
        result (reminders/create id sender recipients message (utc-now))]))
