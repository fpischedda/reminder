(ns reminder.data.reminders
  (:require [monger.collection :as mc]
            [monger.operators :refer :all]
            [reminder.data.connection :refer [database]]
            [reminder.data.utils :refer [gen-id]]))

(defn create [inviting-user invited-user game-id]
  (mc/insert-and-return database "invites" {:_id (gen-id)
                                            :inviting inviting-user
                                            :invited invited-user
                                            :game-id game-id
                                            :status :created}))

(defn update-status [invite-id status]
  (mc/update-by-id database "invites" invite-id {$set {:status status}}))

(defn accept [invite-id]
  (update-status invite-id :accepted))

(defn decline [invite-id]
  (update-status invite-id :declined))
