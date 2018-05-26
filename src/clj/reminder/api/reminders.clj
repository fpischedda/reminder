(ns mazeboard.api.invites
  (:require
   [mazeboard.data.invites :as invites]
   [mazeboard.api.utils :refer [success]]))

(defn received-by-user [req]
  (success "[]"))

(defn sent-by-user [req]
  (success "[]"))

(defn create [req]
  (let [invitation (invites/create "usera" "userb")]
    (success invitation)))

(defn details [req]
  (success "[]"))

(defn accept [req]
  (success "[]"))

(defn decline [req]
  (success "[]"))

(defn delete [req]
  (success "[]"))
