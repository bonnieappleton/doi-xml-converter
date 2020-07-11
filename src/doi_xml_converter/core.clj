(ns doi-xml-converter.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(defn article-json [req]
  {:status  400
   :headers {"Content-Type" "text/html"}
   :body    (str "Invalid DOI: " (-> req :route-params :doi))})

(defroutes app-routes
           (GET "/works/:doi" [doi] article-json)
           (route/not-found "Error, page not found!"))

(defn -main [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8000"))]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

