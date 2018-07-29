(ns reminder.api.handlers
  (:require [bidi.ring :refer [make-handler]]
            [reminder.api.auth :as auth]
            [reminder.api.v1.handlers :as api_v1]))


(def handlers (make-handler ["/" [api_v1/handlers]]))
