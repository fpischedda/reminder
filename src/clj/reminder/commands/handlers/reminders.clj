(ns reminder.commands.handlers.reminders
  (:require [reminder.data.reminders :as reminders]
            [reminder.utils :refer [utc-now]]))

(defn create-remider-handler [_ payload]
  (let [{:keys [reminder-id sender recipients message]} payload]
    (reminders/create reminder-id sender recipients message (utc-now))))
