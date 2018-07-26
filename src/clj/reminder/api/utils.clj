(ns reminder.api.utils
  (:require [bidi.bidi :as bidi]))

(defn respond [body]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "[]"})
