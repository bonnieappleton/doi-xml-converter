(ns doi-xml-converter.article
  (:require [clj-http.client :as client]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.java.io :as io])
  (:import (java.io ByteArrayInputStream)))

(defn parse-xml [xml]
  (-> xml
      .getBytes
      ByteArrayInputStream.
      xml/parse
      zip/xml-zip))

(defn article-from-doi [doi]
  (try (-> (str "https://api.crossref.org/v1/works/" doi "/transform/application/vnd.crossref.unixsd+xml")
           client/get
           :body
           parse-xml
           )
       (catch Exception e nil)))

