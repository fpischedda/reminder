(defproject reminder "0.1.0-SNAPSHOT"
  :description "A tool to send reminders"
  :url "https://francesco.pischedda.info"
  :license {:name "AGPL V3 License"
            :url "https://www.gnu.org/licenses/agpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [http-kit "2.3.0"]
                 [yogthos/config "1.1.1"]
                 [mount "0.1.16"]
                 [metosin/compojure-api "2.0.0-alpha30"]
                 [com.novemberain/monger "3.1.0"]
                 [buddy/buddy-hashers "1.4.0"]
                 [buddy/buddy-auth "2.2.0"]
                 [cheshire "5.8.0"]
                 [clojure.java-time "0.3.2"]
                 [danlentz/clj-uuid "0.1.7"]
                 [ring/ring-json "0.4.0"]]
  :main ^:skip-aot reminder.core
  :plugins [[lein-ring "0.10.0"]
            [lein-midje "3.2.1"]
            [org.clojure/tools.nrepl "0.2.12"]]
  :ring {:handler reminder.api.handlers/app}
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
