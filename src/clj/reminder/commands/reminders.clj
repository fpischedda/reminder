(ns reminder.commands.reminders)

(defn create [sender message recipients]
  {:command :reminder-create
   :payload {:sender sender
             :recipients recipients
             :message message}})

