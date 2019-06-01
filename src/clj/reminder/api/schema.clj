(ns reminder.api.schema
  (:require [schema.core :as s]))

(s/defschema LoginScheme
  {:email s/Str
   :password s/Str})

(s/defschema LoginResult
  {:access_token s/Str
   :expires s/Str})

(s/defschema RegisterProfileScheme
  {:email s/Str
   :password s/Str})

(s/defschema RegisterProfileResult
  {:result (s/enum :success :error)
   (s/optional-key :message) s/Str})
