(ns reminder.utils
  (:require [java-time :as j]))

(defn utc-now []
  (j/instant))
