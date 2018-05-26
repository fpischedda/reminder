(defproject reminder "0.1.0-SNAPSHOT"
  :description "A tool to send reminders"
  :url "https://francesco.pischedda.info"
  :license {:name "AGPL V3 License"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [http-kit "2.2.0"]
                 [yogthos/config "0.9"]
                 [mount "0.1.11"]
                 [compojure "1.6.0"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [bidi "2.1.2"]
                 [com.novemberain/monger "3.1.0"]
                 [buddy/buddy-hashers "1.3.0"]
                 [buddy/buddy-auth "2.1.0"]
                 [cheshire "5.8.0"]
                 [danlentz/clj-uuid "0.1.7"]]
  :main ^:skip-aot reminder.core
  :plugins [[lein-ring "0.8.11"]
            [lein-midje "3.2.1"]
            [org.clojure/tools.nrepl "0.2.12"]]
  :ring {:handler reminder.api.server/app}
  :source-paths ["src/clj" ]
  :target-path "target/%s"
  :test-paths ["test" "test/reminder"]
  :profiles {:uberjar {:aot :all
                       :resource-paths ["config/prod"]}
             :dev {:dependencies [[midje "1.9.1"]
                                  [lein-midje "3.2.1"]
                                  [org.clojure/tools.nrepl "0.2.12"]]
                   :resource-paths ["config/dev"]}}

  :clean-targets ^{:protect false} ["target"]
  )
