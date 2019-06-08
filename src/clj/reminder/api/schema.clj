(ns reminder.api.schema
  (:require [schema.core :as s]))

(s/defschema LoginSchema
  {:email s/Str
   :password s/Str})

(s/defschema LoginResult
  {:access_token s/Str
   :expires s/Str})

(s/defschema RegisterProfileSchema
  {:email s/Str
   :password s/Str})

(s/defschema RegisterProfileResult
  {:result (s/enum :success :error)
   (s/optional-key :message) s/Str})

(s/defschema ReminderCreateSchema
  {:recipients [s/Str]
   (s/optional-key :description) s/Str
   (s/optional-key :schedule) {:start s/Str
                               :end s/Str
                               (s/optional-key :every) s/Int}})

(s/defschema ReminderCreateResult
  {:result (s/enum :success :error)
   (s/optional-key :message) s/Str})
