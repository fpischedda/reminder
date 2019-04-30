(ns reminder.api.routes
  (:require [bidi.bidi :refer [path-for]]
            [reminder.api.auth :as auth]
            [reminder.api.v1.routes :as api_v1]))


(def routes ["/" [api_v1/routes]])

(defn url-for [endpoint &rest]
  (path-for endpoint rest))
