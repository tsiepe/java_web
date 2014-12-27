(ns rot-13.core)
(refer 'clojure.string :only '(join))

(def ascii-chars '(\A \B \C \D \E \F \G \H \I \J \K \L \M \N \O \P \Q \R \S \T \U \V \W \X \Y \Z))
(def german-chars {:Ö "OE" :Ä "AE" :Ü "UE" :ß "SS"})

(defn encrypt
  "Uses the ROT-13 algorithm to encrypt the input text."
  [clearString]
  (join 
    (map #(if (some #{%} ascii-chars) 
            (let 
              [size (count ascii-chars)
               newIdx (+ (.indexOf (apply str ascii-chars) (int %)) 13)] 
              (if (> newIdx (- size 1)) 
                (nth ascii-chars (- newIdx size)) 
                (nth ascii-chars newIdx))) %) 
         (.toCharArray 
           (join 
             (map #(if (contains? german-chars (keyword (str %))) 
                ((keyword (str %)) german-chars) 
                %) (.toCharArray (.toUpperCase clearString))))))))
