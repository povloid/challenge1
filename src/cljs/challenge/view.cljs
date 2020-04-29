(ns challenge.view
  (:require [challenge.events :as events]
            [challenge.subs :as subs]
            [re-frame.core :refer [dispatch subscribe]]))

(defn input-helper [{:keys [type
                            message]}]
  (case type
    :danger  [:p.text-danger
              message]
    :success [:p.text-success
              message]
    nil))

(defn main-page []
  [:div.container
   [:h1 "Scramblies challenge"]
   [:div.form
    [:div.form-group
     [:label "Scramble string"]
     [:input.form-control
      {:placeholder "Enter string"
       :auto-focus  true
       :on-change   #(dispatch [::events/set-input-s1-value (.. % -target -value)])
       :value       @(subscribe [::subs/input-s1-value])}]
     (when-let [o @(subscribe [::subs/input-s1-helper])]
       [input-helper o])]
    [:div.form-group
     [:label "Pattern for arranging"]
     [:input.form-control
      {:placeholder "Enter string"
       :on-change   #(dispatch [::events/set-input-s2-value (.. % -target -value)])
       :value       @(subscribe [::subs/input-s2-value])}]
     (when-let [o @(subscribe [::subs/input-s2-helper])]
       [input-helper o])]
    [:button.btn.btn-primary
     {:on-click #(dispatch [::events/check])}
     "Check"]]
   [:div.mt-4
    (let [{:keys [type
                  message]} @(subscribe [::subs/alert])]
      (case type
        :success [:div.alert.alert-success
                  message]
        :info    [:div.alert.alert-info
                  message]
        :danger  [:div.alert.alert-danger
                  message]
        nil))]])
