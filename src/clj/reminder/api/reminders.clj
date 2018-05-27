(ns reminder.api.reminders
  (:require
   [reminder.data.reminders :as reminders]
   [reminder.api.utils :refer [success]]))

(defn received-by-user [req]
  (success "[]"))

(defn sent-by-user [req]
  (success "[]"))

(defn create [req]
  (let [reminder (reminders/create "usera" "userb")]
    (success reminder)))

(defn details [req]
  (success "[]"))

(defn accept [req]
  (success "[]"))

(defn decline [req]
  (success "[]"))

(defn delete [req]
  (success "[]"))
