(ns reminder.utils
  (:require [java-time :as j]
            [clj-time.format :as f]
            [clj-uuid :as uuid]))

(defn utc-now []
  (j/instant))

(defn gen-id []
  (str (uuid/v1)))

(defn datetime->str [datetime]
  (f/unparse (f/formatter :basic-date-time) datetime))
