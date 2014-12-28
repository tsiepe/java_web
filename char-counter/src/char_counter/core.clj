(ns char-counter.core)

(defn count-chars
  "Counts the number of occurrences of each char in the input string."
  [string]
  (last 
    (let [sortedMap (ref {})]
      (map #(let 
              [currentKey (keyword (str (if (Character/isWhitespace %) \_ %)))] 
              (dosync (ref-set sortedMap 
                              (if (contains? @sortedMap currentKey) 
                                (assoc @sortedMap currentKey (inc (currentKey @sortedMap))) 
                                (assoc @sortedMap currentKey 1))))) 
         (.toCharArray string)))))
