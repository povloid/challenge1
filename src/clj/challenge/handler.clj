(ns challenge.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [taoensso.timbre :as log]))

(defroutes app
  (GET "/" [] "<h1>Hello World</h1>")
  (route/not-found "<h1>Page not found</h1>"))
