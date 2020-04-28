(ns challenge.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]
            [ring.util.response :refer [response]]
            [taoensso.timbre :as log]
            [challenge.string-utils :refer [scramble?]]))

(defroutes handler

  (GET "/" [] "<h1>Hello World</h1>")

  (GET "/api/string/is_scramble/:s1{[a-z]+}/for/:s2{[a-z]+}"
       {{:keys [s1 s2]} :params :as request}
       (log/info ">>> GET > " request)
       (str (scramble? s1 s2)))

  (GET "/api/string/is_scramble/:s1{[a-z]+}/for/:s2{[a-z]+}/json"
       {{:keys [s1 s2]} :params :as request}
       (log/info ">>> GET > " request)
       (response {:result (scramble? s1 s2)}))

  (POST "/api/string/is_scramble"
        {{:keys [s1 s2]} :body :as request}
        (log/info ">>> POST > " request)
        (response {:result (scramble? s1 s2)}))

  (route/not-found "<h1>Page not found</h1>"))


(def app (-> handler
             (wrap-json-body {:keywords? true :bigdecimals? true})
             wrap-json-response))
