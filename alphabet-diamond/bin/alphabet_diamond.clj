(ns alphabet-diamond)
(refer 'clojure.string :only '(join))

(def ascii-chars '(\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z))
(defn print-diamond [one-char] 
  (let [forward-coll (filter #(<= (int %) (int one-char)) (seq ascii-chars))
        reverse-coll (rest (reverse forward-coll))
        top (loop [fresult forward-coll, fcounter (- (count forward-coll) 1), fspace-counter 0] 
            (let [leading-space (join (repeat fcounter " "))
                  a-char (apply str (cons (first fresult) '("")))
                  in-between-space (join (repeat (* fspace-counter 2) " "))
                  remainder (if (= a-char "a") a-char (str a-char in-between-space a-char))]
              (println leading-space remainder))
             (if (pos? fcounter) (recur (rest fresult) (dec fcounter) (inc fspace-counter))))

        bottom (loop [rresult reverse-coll, rcounter 1, rspace-counter (- (* (count reverse-coll) 2) 2)] 
            (let [leading-space (join (repeat rcounter " "))
                  a-char (apply str (cons (first rresult) '("")))
                  in-between-space (join (repeat rspace-counter " "))
                  remainder (if (= a-char "a") a-char (str a-char in-between-space a-char))]
              (println leading-space remainder))
             
             (if (< rcounter (count reverse-coll)) (recur (rest rresult) (inc rcounter) (- rspace-counter 2))))]))
