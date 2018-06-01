(ns reminder.api.reminders
  (:require
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.api.utils :refer [success]]))

(def reminder-dispatcher (dispatcher/create {:reminder-create 'handlers/create
                                             :reminder-close 'handlers/close}))
(defn received-by-user [req]
  (success "[]"))

(defn sent-by-user [req]
  (success "[]"))

(defn create [req]
  (let [result (commands/create "usera" "reminder!" [])]
    (if (= :created (:result result))
      (success :created)
      (success (:error result)))))

(defn details [req]
  (success "[]"))

(defn accept [req]
  (success "[]"))

(defn decline [req]
  (success "[]"))

(defn delete [req]
  (success "[]"))
