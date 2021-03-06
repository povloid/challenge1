(ns challenge.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]
            [ring.util.response :refer [response
                                        redirect]]
            [taoensso.timbre :as log]
            [challenge.string-utils :refer [scramble?]]))

(defroutes handler
  (GET "/" req
       (redirect "/index.html"))

  (GET "/api/string/is_scramble/:s1{^[a-z]+$}/for/:s2{^[a-z]+$}"
       {{:keys [s1 s2]} :params :as request}
       (log/info ">>> GET > " request)
       (str (scramble? s1 s2)))

  (GET "/api/string/is_scramble/:s1{^[a-z]+$}/for/:s2{^[a-z]+$}/json"
       {{:keys [s1 s2]} :params :as request}
       (log/info ">>> GET > " request)
       (response {:result (scramble? s1 s2)}))

  ;; TODO: need to add serverside validation for POST, may be it will be clojure spec.
  (POST "/api/string/is_scramble"
        {{:keys [s1 s2]} :body :as request}
        (log/info ">>> POST > " request)
        (response {:result (scramble? s1 s2)}))

  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))


(def app (-> handler
             (wrap-json-body {:keywords? true :bigdecimals? true})
             wrap-json-response))
