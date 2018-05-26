(ns reminder.data.utils
  (:require [monger.query :as mq]
            [clj-uuid :as uuid]
            [reminder.data.connection :refer [database]]))

(defn paged-filter [database collection page page-size filters-map]
  (mq/with-collection database collection
    (mq/find filters-map)
    (mq/skip (* page page-size))
    (mq/limit page-size)))

(defn gen-id []
  (str (uuid/v1)))
