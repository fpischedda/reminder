(ns reminder.commands.dispatcher)

(defn no-handler [name payload]
  (println "No handler defined for command " name " with payload " payload)
  {:error {:code :missing-handler}})

(defn create [command-handlers]
  (fn [{:keys [name payload]} command]
    (let [handler (get command-handlers name no-handler)]
      (handler name payload))))
