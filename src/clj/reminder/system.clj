(ns reminder.system
  (:require [mount.core :refer [defstate]]
            [mazeboard.config :refer [config]]
            [mazeboard.data.connection :refer [database]]
            [mazeboard.api.server :refer [server]]))

(defstate system
  :start {:config config
          :database database
          :server server})
