(ns russian-multiplication.core)

(defn- russian-multiply
  "Russian peasant's multiplication algorithm."
  [x y ys]
  (let [internal-ys (if (odd? x) (conj ys y) ys)]
    (if (and (> x 0) (> y 0)) (if (= x 1) (reduce + internal-ys) (russian-multiply (int (/ x 2)) (* y 2) internal-ys)) 0)))

(defn russian-peasant-multiply 
  [x y]
  (russian-multiply x y []))
