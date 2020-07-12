(ns doi-xml-converter.article
  (:require [clj-http.client :as client]))

(defn article-from-doi [doi]
  (try (-> (str "https://api.crossref.org/v1/works/" doi "/transform/application/vnd.crossref.unixsd+xml")
           client/get
           :body)
       (catch Exception e nil)))

