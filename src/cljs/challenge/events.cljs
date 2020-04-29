(ns challenge.events
  (:require [ajax.core :refer [POST]]
            [challenge.db :as db]
            [re-frame.core :refer [dispatch reg-event-db reg-event-fx reg-fx]]
            [taoensso.timbre :as log]))

(reg-event-fx
 :initialize-db
 (fn [_ _]
   {:db db/default-db}))


(defn change [old-value new-value]
  (if (.test #"^[a-z]*$" new-value)
    new-value
    old-value))

(reg-event-db
 ::set-input-s1-value
 (fn [db [_ value]]
   (update db :input-s1 change value)))

(reg-event-db
 ::set-input-s2-value
 (fn [db [_ value]]
   (update db :input-s2 change value)))


(reg-event-fx
 ::check
 (fn [{{:keys [input-s1 input-s2] :as db} :db} _]
   {:db    (-> db
               (dissoc :alert))
    ::post {:s1 input-s1
            :s2 input-s2}}))

(reg-fx
 ::post
 (fn [params]
   (POST "/api/string/is_scramble"
         {:params          params
          :format          :json
          :response-format :json
          :keywords?       true
          :handler         #(dispatch [::post-success %])
          :error-handler   #(dispatch [::post-faliure %])})))

(reg-event-db
 ::post-success
 (fn [db [_ {:keys [result]
             :as response}]]
   (log/info response)
   (-> db
       (assoc :alert (if result
                       {:type    :success
                        :message "Yes, it can be."}
                       {:type    :info
                        :message "No, it can't be."})))))

(reg-event-db
 ::post-failure
 (fn [db [_ error]]
   (log/error error)
   (-> db
       (assoc :alert {:type    :danger
                      :message (str "Error: " error)}))))
