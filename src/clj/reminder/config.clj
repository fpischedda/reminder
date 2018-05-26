(ns reminder.config
  (:require [mount.core :refer [defstate]]
            [config.core :refer [env]]))

(defstate config
  :start env)
