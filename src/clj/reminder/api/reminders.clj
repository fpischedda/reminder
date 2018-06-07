(ns reminder.api.reminders
  (:require
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.api.utils :refer [success]]))

(def reminder-dispatcher (dispatcher/create {:reminder/create 'handlers/create
                                             :reminder/close 'handlers/close}))
(defn reminder-id [req]
  (:id (:route-params req)))

(defn received-by-user [req]
  (success "[]"))

(defn sent-by-user [req]
  (success "[]"))

(defn create [req]
  (let [result (reminder-dispatcher
                (commands/create "usera" "reminder!" ["userb"]))]
    (if (= :created (:result result))
      (success :created)
      (success (:error result)))))

(defn details [req]
  (success "[]"))

(defn accept [req]
  (success "[]"))

(defn decline [req]
  (success "[]"))

(defn close [req]
  (let [paylad (:json req)
        reason (:reason req)
        result (reminder-dispatcher
                (commands/close (reminder-id req) reason "usera"))]
    (if (= :closed (:result result))
      (success :closed)
      (success (:error result)))))

(defn delete [req]
  (success "[]"))
