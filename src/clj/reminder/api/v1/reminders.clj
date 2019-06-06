(ns reminder.api.v1.reminders
  (:require
   [compojure.api.sweet :refer :all]
   [ring.util.http-response :as response]
   [schema.core :as s]
   [reminder.commands.reminders :as commands]
   [reminder.commands.dispatcher :as dispatcher]
   [reminder.commands.handlers.reminders :as handlers]
   [reminder.utils :refer [gen-id]]))

(def reminder-dispatcher (dispatcher/create {:reminder/create 'handlers/create
                                             :reminder/close 'handlers/close}))

(s/defschema ReminderCreate
  {:name s/Str
   (s/optional-key :description) s/Str
   :destinations [s/Str]})

(defn reminder-id [req]
  (:id (:route-params req)))

(defn create [req]
  (let [id (gen-id)
        creator "usera"
        message "message for you"
        recipients ["peter griffin" "philip j fry"]
        result (reminder-dispatcher
                (commands/create id creator message recipients))]
    (if (= :created (:result result))
      ;; (response/created (path-for :reminders/get :id (:id result)))
      (response/created "test")
      (response/bad-request (:error result)))))

(defn details [req]
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

(def handlers
  (context "/reminder" []
    :tags ["reminder"]
    (POST "/" []
      :body [reminder ReminderCreate]
      :summary "create a reminder"
      (create reminder))
    ))
