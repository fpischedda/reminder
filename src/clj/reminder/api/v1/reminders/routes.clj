(ns reminder.api.v1.reminders.routes)

(def routes ["" {:get {"/received" :reminders/received-by-user
                       "/sent" :reminders/sent-by-user
                       "/" {[:id ""] :reminders/get}}
                 :post {"" :reminders/create}
                 :delete {"" :reminders/delete}}])
