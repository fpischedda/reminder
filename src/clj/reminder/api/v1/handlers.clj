(ns reminder.api.v1.handlers
  (:require [reminder.api.v1.reminders.routes :as reminders]))

(def handlers ["v1/" {"reminders" [reminders/routes]}])
