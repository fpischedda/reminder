(ns reminder.api.v1.reminders.api
  (:require
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.api.routes :refer [url-for]]
   [reminder.api.response :as response]))

(def reminder-dispatcher (dispatcher/create {:reminder/create 'handlers/create
                                             :reminder/close 'handlers/close}))
(defn reminder-id [req]
  (:id (:route-params req)))

(defn received-by-user [req]
  (response/no-content))

(defn sent-by-user [req]
  (response/no-content))

(defn create [req]
  (let [result (reminder-dispatcher
                (commands/create "usera" "reminder!" ["userb"]))]
    (if (= :created (:result result))
      (response/created (url-for :reminders/get :id (:id result)))
      (response/bad-request (:error result)))))

(defn details [req]
  (response/no-content))

(defn accept [req]
  (response/no-content))

(defn decline [req]
  (response/no-content))

(defn close [req]
  (let [payload (:json req)
        reason (:reason payload)
        result (reminder-dispatcher
                (commands/close (reminder-id req) reason "usera"))]
    (if (= :closed (:result result))
      (response/no-content)
      (response/bad-request (:error result)))))

(defn delete [req]
  (response/no-content))

(def handlers ["" {:get {"/received" received-by-user
                         "/sent" sent-by-user
                         "/" {[:id ""] details}}
                   :post {"" create}
                   :put {"/" {[:id "/close"] close}}
                   :delete {"/" {[:id ""] delete}}}])
