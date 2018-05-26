(ns reminder.api.routes
  (:require [compojure.core :refer [defroutes context GET POST PATCH DELETE ANY OPTIONS]]
            [reminder.api.auth :as auth]
            [reminder.api.reminders :as reminders]))

(defroutes routes
  (context "/api/v1" []
           (OPTIONS ":url{.*}" [url] "")
           (POST "/login" [] auth/login)
           (POST "/register" [] auth/register)
           (context "/reminders" []
                    (GET "/" [] reminders/received-by-user)
                    (POST "/" [] reminders/create)
                    (context "/:id" [id]
                             (GET "/" [] reminders/details)
                             (POST "/accept" [] reminders/accept)
                             (POST "/decline" [] reminders/decline)
                             (DELETE "/" [] reminders/delete)))))

