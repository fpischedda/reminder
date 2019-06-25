(ns reminder.commands.reminders)

(defn create [reminder-id sender message recipients]
  {:command :reminder/create
   :payload {:reminder-id reminder-id
             :sender sender
             :recipients recipients
             :message message}})

(defn close [reminder-id reason actor]
  {:command :reminder/close
   :payload {:reminder-id reminder-id
             :reason reason
             :actor actor}})
