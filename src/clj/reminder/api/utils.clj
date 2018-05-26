(ns reminder.api.utils)

(defn success [body]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "[]"})
