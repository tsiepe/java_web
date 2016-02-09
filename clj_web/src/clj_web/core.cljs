(ns clj_web.core
  (:require [clojure.browser.repl :as repl]))

(defonce conn 
  (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Hello Clojars! We are the clochards...")

(defn adder [a b]
  (+ a b))

(defn mult [a b]
  (* a b))

