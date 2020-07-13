(ns doi-xml-converter.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [doi-xml-converter.article :as article]
            [cheshire.core :refer :all])
  (:gen-class))

(defmulti article-json (fn [{{:keys [doi]} :route-params}] (some? (re-matches #"^10\.\d+\/\w+$" doi))))

(defmethod article-json true [{{:keys [doi]} :route-params}]
  (let [response-json (article/article-from-doi doi)]
    (if (some? response-json)
      {:status  200
       :headers {"Content-Type" "text/json"}
       :body    (generate-string response-json)}
      {:status  404
       :headers {"Content-Type" "text/json"}
       :body    (str "DOI " doi " does not exist")})))

(defmethod article-json false [{{:keys [doi]} :route-params}]
  {:status  400
   :headers {"Content-Type" "text/json"}
   :body    (str "Invalid DOI: " doi)})

(defroutes app-routes
           (GET "/works/:doi{.*}" [doi] article-json)
           (route/not-found "Error, page not found!"))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8000"))]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

