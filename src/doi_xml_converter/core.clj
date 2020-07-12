(ns doi-xml-converter.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(defn article-json [req]
  (if (re-matches #"^10\.\d+\/\w+$" (-> req :route-params :doi))
    {:status  404
     :headers {"Content-Type" "text/json"}
     :body    (str "DOI " (-> req :route-params :doi) " does not exist")}
    {:status  400
     :headers {"Content-Type" "text/json"}
     :body    (str "Invalid DOI: " (-> req :route-params :doi))}))

(defroutes app-routes
           (GET "/works/:doi{.*}" [doi] article-json)
           (route/not-found "Error, page not found!"))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8000"))]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

