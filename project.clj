(defproject challenge "0.1.0"
  :description "the Clojure challenge"
  :url "https://clojure.org"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [compojure "1.6.1"]
                 [com.taoensso/timbre "4.10.0"]
                 [ring/ring-json "0.5.0"]]

  :jvm-opts ^:replace ["-Xmx500m" "-server" "-Dfile.encoding=UTF-8"]

  :plugins [[lein-ring "0.12.5"]]

  :ring {:handler       challenge.handler/app
         :auto-reload?  false
         :auto-refresh? false
         :uberwar-name  "challenge.war"
         :port          3000}

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"]

  :source-paths ["src/cljc"
                 "src/clj"
                 "src/cljs"]

  :profiles {:dev {:dependencies []}

             :uberwar {:aot         :all
                       :omit-source true}
             :war     {:aot         :all
                       :omit-source true}
             :uberjar {:aot         :all
                       :omit-source true}
             :jar     {:aot         :all
                       :omit-source true}}
  )
