(ns roman-numerals)
(refer 'clojure.string :only '(join))

(def latins [\I, \V, \X, \L, \C, \D, \M])
(def arabics [1, 5, 10, 50, 100, 500, 1000])
(def arabic-to-roman-digit (zipmap arabics latins))

(defn translate-digit [al-zifr power] 
  (let [; check if arabics contains the requested number
         matched-zifr (if (> al-zifr 0) (int (* (if (some #(= al-zifr %) arabics) (double al-zifr) (last (filter #(< % al-zifr) arabics))) (Math/pow 10 power))))
         index-of-al-zifr (if (> al-zifr 0) (first (keep-indexed #(if (= %2 matched-zifr) %1) arabics)))
         higher-neighbour (if (> al-zifr 0) (get arabics (+ index-of-al-zifr 1)))
         lesser-neighbour (if (> al-zifr 0) (get arabics (- index-of-al-zifr 1)))
         ] (join (cond (= al-zifr 0) ""
                       (< al-zifr 4) 
                            (join (repeat al-zifr (get arabic-to-roman-digit matched-zifr)))
                       (= al-zifr 4)
                            (str (get arabic-to-roman-digit matched-zifr) (get arabic-to-roman-digit higher-neighbour))
                       (= al-zifr 5)
                            (str (get arabic-to-roman-digit matched-zifr))
                       (< al-zifr 9)
                            (str (get arabic-to-roman-digit matched-zifr) (join (repeat (- al-zifr 5) (get arabic-to-roman-digit lesser-neighbour))))
                       :else
                            (str (get arabic-to-roman-digit lesser-neighbour) (get arabic-to-roman-digit higher-neighbour)))) ))

(defn -print-roman-numeral-for-arabic [number]
  (let [stringified (join (reverse (str number)))
        number-digits (.length stringified)]
    (if (or (> number-digits 4) (> number 3999)) 
      (println "Sorry mate! No can do. Dunno how tae translate such big a number.") 
      (loop [i (- number-digits 1)] 
        (let [translated (cond 
                           (= i 3) (let [num-value (Character/digit (.charAt stringified i) 10)
                                         latin-value (join (repeat num-value (get arabic-to-roman-digit 1000)))] (str latin-value))
                           :else
                               (translate-digit (Character/digit (.charAt stringified i) 10) i)
                           )] (print translated))
        (if (> i 0) (recur (dec i)))))))
