(ns challenge.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::input-s1-value
 (fn [db]
   (db :input-s1)))

(reg-sub
 ::input-s2-value
 (fn [db]
   (db :input-s2)))

(reg-sub
 ::alert
 :alert)
