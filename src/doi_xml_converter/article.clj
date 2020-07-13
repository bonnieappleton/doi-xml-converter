(ns doi-xml-converter.article
  (:require [clj-http.client :as client]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.java.io :as io])
  (:import (java.io ByteArrayInputStream)))

(defn crm-content [query-list name]
  (->> query-list
       (filter #(= (:tag %) :crm-item))
       (filter #(= (:name (:attrs %)) name))
       first
       :content
       first))

(defn article-info-map [query-list]
  {:is-referenced-by-count (crm-content query-list "citedby-count")
   :publisher              (crm-content query-list "publisher-name")
   :member                 (crm-content query-list "member-id")})


(defn query-tag [xml-map]
  (get-in xml-map [0 :content 0 :content 1 :content 0 :content]))

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
           query-tag
           article-info-map)
       (catch Exception e nil)))

