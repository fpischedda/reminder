(ns reminder.api.handlers
  (:require [compojure.api.sweet :refer :all]
            [reminder.api.auth :as auth]
            [reminder.api.scheme :refer :all]))

(def handlers
  (api
    {:swagger
     {:ui "/doc"
      :spec "/swagger.json"
      :data {:info {:title "Re:minder-api"
                    :description "Re:minder Api Documentation"}
             :tags [{:name "api", :description "Re:minder apis"}]}}}
    (context "/oauth" []
      :tags ["oauth"]
      (POST "/login" []
        :return LoginResult
        :body [credentials LoginScheme]
        :summary "Try to login providing username and password"
        (auth/login credentials))
      (POST "/register" []
        :return RegisterProfileResult
        :body [profile RegisterProfileScheme]
        :summary "Try to login providing username and password"
        (auth/register profile)))
    (context "/api" []
      :tags ["api"])))
