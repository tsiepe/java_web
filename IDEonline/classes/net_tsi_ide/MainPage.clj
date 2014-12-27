(ns net_tsi_ide.MainPage (:gen-class :extends javax.servlet.http.HttpServlet))

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
          "<h1>Hello my friend "
          (.getParameter request "yourname")
          "</h1>"
          "</body>"
          "</html>"))))

(defn -doPost [_ request response]
  (-doGet nil request response))