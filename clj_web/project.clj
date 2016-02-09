(defproject clj_web "0.0.1-SNAPSHOT"
  :description "First tiny web project using ring and compojure in combination with reloadable apps."
;;  :repositories {"project" "file:repo"}
  :dependencies [
	[org.clojure/clojure "1.6.0"]
	[ring/ring-core "1.4.0"] 
	[ring/ring-jetty-adapter "1.4.0"]
	[ring/ring-devel "1.4.0"]
        [compojure "1.4.0"]
        [hiccup "1.0.5"]
        [org.clojure/clojurescript "1.7.228"]
        [com.datomic/datomic-pro "0.9.5302" :exclusions [joda-time]]]
  :plugins [[lein-ring "0.8.11"]]
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo" :creds :gpg}}
  :ring {:handler clj_web.core/reloadable-app})
