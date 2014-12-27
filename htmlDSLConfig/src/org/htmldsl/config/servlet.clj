(ns org.htmldsl.config.servlet
  (:use [clojure.java.jdbc :only [with-connection with-query-results]])
(:gen-class :extends javax.servlet.http.HttpServlet))

(defn -doGet
  [_ request response]
  (.setContentType response "text/html")
  (let [w (.getWriter response)]
      (.println w
        (str "<html>"
          "<head>"
          "<title>Hello World!</title>"
          "</head>"
          "<body>"
          "<h1>Hello "
          (.getParameter request "Name")
          "</h1>"
          "<form method=\"post\" action=\"hello\">"
          "<label for=\"dbvalue\">Save sth. to the database: </label>" 
          "<input id=\"dbvalue\" type=\"text\" name=\"dbvalue\">"
          "<label for=\"post\"> </label>" 
          "<input id=\"post\" type=\"submit\" value=\"Post\">"
          "</form>"
          "</body>"
          "</html>"))))

(defn -doPost [_ request response]
  ((let [db-host "localhost"
        db-port 3306
        db-name "test"]
    (def db {:classname "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           :user "root"
           :password "isa0207"})
    (with-connection db
      (with-query-results rs ["select * from htmldsl"]
        (dorun (map #(println (:config_key :config_value %)) rs)))))


    ; rs will be a sequence of maps,
    ; one for each record in the result set.
    ))