(ns reminder.api.handlers
  (:require [compojure.api.sweet :refer :all]))

(def handlers
  (api
    {:swagger
     {:ui "/doc"
      :spec "/swagger.json"
      :data {:info {:title "Re:minder-api"
                    :description "Re:minder Api Documentation"}
             :tags [{:name "api", :description "Re:minder apis"}]}}}

    (context "/api" []
      :tags ["api"])))
