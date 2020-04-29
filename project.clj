(defproject challenge "0.1.0"
  :description "The Clojure challenge."
  :url "https://clojure.org"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [compojure "1.6.1"]
                 [com.taoensso/timbre "4.10.0"]
                 [ring/ring-json "0.5.0"]
                 [reagent "0.10.0"]
                 [re-frame "0.12.0"]
                 [cljs-ajax "0.8.0"]]

  :jvm-opts ^:replace ["-Dfile.encoding=UTF-8"]

  :plugins [[lein-cljsbuild "1.1.8"]
            [lein-ring "0.12.5"]
            [lein-figwheel "0.5.19"]]

  :ring {:handler       challenge.handler/app
         :auto-reload?  false
         :auto-refresh? false
         :uberwar-name  "challenge.war"
         :port          3000}

  :auto-clean false
  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"]

  :resource-paths ["resources"]

  :source-paths ["src/cljc"
                 "src/clj"
                 "src/cljs"]

  :figwheel {:ring-handler challenge.handler/app
             :nrepl-port   7888}

  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.19"]]}

             :uberwar {:aot         :all
                       :omit-source true}
             :war     {:aot         :all
                       :omit-source true}
             :uberjar {:aot         :all
                       :omit-source true}
             :jar     {:aot         :all
                       :omit-source true}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "challenge.core/mount-root"}
     :compiler     {:output-to      "resources/public/js/app.js"
                    :output-dir     "resources/public/js/out"
                    :asset-path     "js/out"
                    :main           challenge.core
                    :optimizations  :none
                    :source-map     true
                    :cache-analysis true}}
    {:id           "prod"
     :source-paths ["src/cljs"]
     :compiler     {:output-to      "resources/public/js/app.js"
                    :main           challenge.core
                    :optimizations  :advanced
                    :cache-analysis true}}]})
