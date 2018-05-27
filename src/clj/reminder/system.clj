(ns reminder.system
  (:require [mount.core :refer [defstate]]
            [reminder.config :refer [config]]
            [reminder.data.connection :refer [database]]
            [reminder.api.server :refer [server]]))

(defstate system
  :start {:config config
          :database database
          :server server})
