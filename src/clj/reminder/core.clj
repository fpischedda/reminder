(ns reminder.core
  (:require [mount.core :as mount]
            [reminder.system :refer [system]])
  (:gen-class))

(defn -main
  "Reminder server executable"
  [& args]
  (println "starting server: " (:server (:config system)))
  (mount/start))
