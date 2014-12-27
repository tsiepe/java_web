(ns lucky-numbers.core)

(defn- isLuckyNumber?
  "Returns true if and only if the number's digits' squared summed up eventually yield the number 1 after recursively going through this procedure."
  [number thisFnStackDepth]
  (let [intNumber (int number)]
    (if (and (< thisFnStackDepth 200) (> intNumber 1)) (isLuckyNumber? (reduce + (map #(Math/pow (Integer/valueOf (str %)) 2) (.toCharArray (str intNumber)))) (inc thisFnStackDepth)) (= intNumber 1))))

(defn luckyNumber?
  [number]
  (isLuckyNumber? number 0))