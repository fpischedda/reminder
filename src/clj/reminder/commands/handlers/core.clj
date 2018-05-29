(ns reminder.commands.handlers.core)

(def *handlers* {})

(defn register [command handler-fn]
  (if-let [entries (get *handlers* command)]
    (into entries handler-fn)
    [handler-fn]))
  
