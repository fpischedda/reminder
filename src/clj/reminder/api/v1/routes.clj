(ns reminder.api.v1.routes
  (:require [reminder.api.v1.reminders :as reminders]))

(def routes ["v1/" {"reminders" [reminders/routes]}])
