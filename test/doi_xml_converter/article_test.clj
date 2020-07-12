(ns doi-xml-converter.article-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.article :refer :all]))

(deftest article-from-doi-test
  (testing "should return nil if doi does not exist"
    (is (= nil (article-from-doi "10.5555/1111111")))))