(ns reminder.commands.reminders)

(defn create [sender message recipients]
  {:command :reminder/create
   :payload {:sender sender
             :recipients recipients
             :message message}})

(defn close [reminder-id reason actor]
  {:command :reminder/close
   :payload {:reminder-id reminder-id
             :reason reason
             :actor actor}})

