(ns clj_web.core
  (:require [clojure.browser.repl :as repl]))

(defonce conn
  (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Hello Clojars!")

(defn adder [a b]
  (+ a b))

(defn mult [a b]
  (* a b))
