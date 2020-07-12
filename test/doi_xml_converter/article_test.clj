(ns doi-xml-converter.article-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.article :refer :all]
            [clj-http.client :as client]))

(deftest article-from-doi-test
  (testing "should return nil if doi does not exist"
    (with-redefs (client/get (fn [_] {:status 404}))
      (is (= nil (article-from-doi "doi-does-not-exist")))))
  (testing "should return article body if doi exists"
    (with-redefs (client/get (fn [_] {:body "article-body"}))
      (is (= "article-body" (article-from-doi "doi-exists"))))))