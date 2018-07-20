(ns reminder.api.utils)

(defn success [{:keys [body status-code headers] :or {body ""
                                                      status 200
                                                      headers {}}}]
  {:status status
   :headers headers
   :body body})
