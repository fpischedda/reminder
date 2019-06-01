(ns reminder.utils
  (:require [java-time :as j]
            [clj-uuid :as uuid]))

(defn utc-now []
  (j/instant))

(defn gen-id []
  (str (uuid/v1)))

(defn datetime->str [datetime]
  (str datetime))
