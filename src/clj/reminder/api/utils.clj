(ns reminder.api.utils)

(defn id-from-request [req]
  (get-in req [:route-params :is]))
