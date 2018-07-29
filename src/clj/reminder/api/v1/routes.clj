(ns reminder.api.v1.routes
  (:require [reminder.api.v1.reminders.routes :as reminders]))

(def routes ["v1/" {"reminders" [reminders/routes]}])
