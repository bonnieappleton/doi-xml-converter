(ns doi-xml-converter.article
  (:require [clj-http.client :as client]
            [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.java.io :as io])
  (:import (java.io ByteArrayInputStream)))

(defn- tag-content [xml-map tag]
  (->> xml-map
       (filter #(= tag (:tag %)))
       first
       :content))

(defn title-from [query-list]
  (-> query-list
       (tag-content :doi_record)
       (tag-content :crossref)
       (tag-content :journal)
       (tag-content :journal_article)
       (tag-content :titles)
       (tag-content :title)))

(defn query-tag [xml-map]
  (-> xml-map
      (tag-content :crossref_result)
      (tag-content :query_result)
      (tag-content :body)
      (tag-content :query)))

(defn crm-content-from [query-list name]
  (->> query-list
       (filter #(= (:tag %) :crm-item))
       (filter #(= (:name (:attrs %)) name))
       first
       :content
       first))

(defn parse-xml [xml]
  (-> xml
      .getBytes
      ByteArrayInputStream.
      xml/parse
      zip/xml-zip))

(defn article-info-map [query-list doi]
  {:DOI                    doi
   :is-referenced-by-count (crm-content-from query-list "citedby-count")
   :publisher              (crm-content-from query-list "publisher-name")
   :type                   :journal-article
   :title                  (title-from query-list)
   :member                 (crm-content-from query-list "member-id")})

(defn- xml-from-crossref [doi]
  (client/get (str "https://api.crossref.org/v1/works/" doi "/transform/application/vnd.crossref.unixsd+xml")))

(defn article-from-doi [doi]
  (try (-> doi
           xml-from-crossref
           :body
           parse-xml
           query-tag
           (article-info-map doi))
       (catch Exception e nil)))

