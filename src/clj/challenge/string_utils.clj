(ns challenge.string-utils)


(defn delete-first [c s]
  (loop [acc [] s s]
    (if (empty? s) acc
        (let [[cs & s] s]
          (if (= c cs)
            (concat acc s)
            (recur (conj acc cs) s))))))


(defn scramble? [s1 s2]
  (->> s1
       (reduce (fn [s2 c]
                 (let [s2 (delete-first c s2)]
                   (if (empty? s2)
                     (reduced nil)
                     s2)))
               s2)
       (nil?)))


(defn test []
  [(is (= (scramble? "rekqodlw"          "world")    true))
   (is (= (scramble? "cedewaraaossoqqyt" "codewars") true))
   (is (= (scramble? "katas"             "steak")    false))])
