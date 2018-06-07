(ns reminder.commands.dispatcher)

(defn no-handler [command payload]
  (println "No handler defined for command " command " with payload " payload)
  {:error {:code :missing-handler}})

(defn create [command-handlers]
  (fn [{:keys [name payload]}]
    (let [handler (get command-handlers name no-handler)]
      (handler name payload))))
