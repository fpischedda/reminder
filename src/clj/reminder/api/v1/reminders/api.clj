(ns reminder.api.v1.reminders.api
  (:require
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.api.routes :refer [url-for]]
   [reminder.api.utils :refer [respond]]))

(def reminder-dispatcher (dispatcher/create {:reminder/create 'handlers/create
                                             :reminder/close 'handlers/close}))
(defn reminder-id [req]
  (:id (:route-params req)))

(defn create [req]
  (let [result (reminder-dispatcher
                (commands/create "usera" "reminder!" ["userb"]))]
    (if (= :created (:result result))
      (respond {:headers {"Location" (url-for :reminders/get :id (:id result))}
                :status 201})
      (respond {:body (:error result) :status 400}))))

(defn details [req]
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

(def handlers ["" {:get {"/received" received-by-user
                         "/sent" sent-by-user
                         "/" {[:id ""] details}}
                   :post {"" create}}])
