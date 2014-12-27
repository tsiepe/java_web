(ns xmas-tree.core)
(refer 'clojure.string :only '(join))

(defn draw-tree-to-height
  "I draw a christmas tree to the given height."
  [height hasStar]
  (if (true? hasStar) 
    (println (join (repeat (- height 1) " ")) "*"))
  (loop [count 1 width 1 spacing height] 
    (print (join (repeat spacing " "))) (println (join (repeat width "x"))) (if (< count height) (recur (+ count 1) (if (even? (+ width 1)) (+ width 2) (+ width 1)) (- spacing 1))))
  (print (join (repeat height " "))) (println "x"))
