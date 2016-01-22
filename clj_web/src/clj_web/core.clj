(ns clj_web.core
  (:require 
   [compojure.core :refer [GET POST defroutes]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.adapter.jetty :refer :all]
   [datomic.api :refer [query connect release tempid]]
   [hiccup.core :refer :all]))
;; defining some routes just for the heck of it
(defroutes app
  (GET "/" [] "Get Off Of My Cloud Now, says freckle, so wiggle it just a little bit!")
  (GET "/motto" [] "Cleanliness is next to godliness!")
  (GET "/wisdom" [] "You think you know when you learn, <br/> are more sure when you can write, <br/>  even more when you can teach, <br/> but certain when you can program.<br/><cite>[Alan Perlis, Yale University computer scientist]</cite> ")
  (GET "/login" [] (str "<html>" 
                        "<head/>"
                        "<body>"
                        "<h1>Certum quod factum.</h1>"
                        "<form action=\"/datomic\" method=\"post\">"
                        "<input type=\"text\" name=\"user\">"
                        "<input type=\"submit\">"
                        "</form>"
                        "</body>"
                        "</html>"))
  (GET "/question/:battlestar" [request battlestar]
       (str request)
       (if (= battlestar "Anakin") 
         (str "I'm your father!")
         (str "Who dares speak out loud?")))
  (GET "/test" [] 
       (html [:html 
              [:head [:script {:type "text/javascript" :src "out/main.js"}]]
              [:body 
               [:h1#one-and-only.big-wig "See what gives, hen...!"]
               [:canvas#circle {:width "200px" :height "100px"}]]]))
  (GET "/vision" request
     (str request " There are now machines that think."))
  (POST "/datomic" request
        (let [user (get (:params request) :user)
                         dev-uri "datomic:dev://localhost:4334/demo"
                         devconn (connect dev-uri)
                         devdatom ["db/add" (tempid "db.part/user") "db/doc" user]]
                     (.transact devconn [devdatom])
                     (release devconn)
                     "Done storing stuff."))
  (GET "/datomic" []
       (let [dev-uri "datomic:dev://localhost:4334/demo"
             devconn (connect dev-uri)
             dibie (.db devconn)
             result (query {:query "[:find (pull ?entity [*]) :where [?entity :db/doc \"Hi you devel...!\"]]" :args [dibie]})]
             (release devconn)
             {:status 200
              :headers {"Content-Type" "text/html; charset=utf-8"}
              :body (str (clojure.string/join "<br/>" result) "<br/>Done displaying stuff.")}
)))
(def reloadable-app
  (wrap-reload app))
