(ns reminder.api.v1.reminders
  (:require
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.api.utils :refer [respond]]))

(def reminder-dispatcher (dispatcher/create {:reminder/create 'handlers/create
                                             :reminder/close 'handlers/close}))
(defn reminder-id [req]
  (:id (:route-params req)))

(defn received-by-user [req]
  (respond "[]"))

(defn sent-by-user [req]
  (respond "[]"))

(defn create [req]
  (let [result (reminder-dispatcher
                (commands/create "usera" "reminder!" ["userb"]))]
    (if (= :created (:result result))
      (respond :created)
      (respond (:error result)))))

(defn details [req]
  (respond "[]"))

(defn accept [req]
  (respond "[]"))

(defn decline [req]
  (respond "[]"))

(defn close [req]
  (let [paylad (:json req)
        reason (:reason req)
        result (reminder-dispatcher
                (commands/close (reminder-id req) reason "usera"))]
    (if (= :closed (:result result))
      (respond :closed)
      (respond (:error result)))))

(defn delete [req]
  (respond "[]"))

(def routes ["" {:get {"/received" received-by-user
                       "/sent" sent-by-user
                       "/" {[:id ""] details}}
                 :post {"" create}}])
