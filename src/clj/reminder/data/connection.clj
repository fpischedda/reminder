(ns reminder.data.connection
  (:require [monger.core :as mg]
            [mount.core :refer [defstate]]
            [reminder.config :refer [config]]))

(defstate connection
  :start (mg/connect-via-uri (:mongodb config))
  :stop (mg/disconnect (:conn connection)))

(defstate database
  :start (:db connection))
