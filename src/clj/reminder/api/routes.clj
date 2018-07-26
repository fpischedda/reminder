(ns reminder.api.routes
  (:require [reminder.api.auth :as auth]
            [reminder.api.v1.routes :as api_v1]
            [reminder.api.v1.reminders :as reminders]))


(def routes ["/" [api_v1/routes]])
