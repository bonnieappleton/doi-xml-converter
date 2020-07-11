(ns doi-xml-converter.core-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.core :refer :all]
            [ring.mock.request :as mock]))

(deftest a-test
  (testing "should return 404 response for GET request to /not-an-endpoint"
    (let [response (app-routes (mock/request :get "/not-an-endpoint"))]
      (is (= 404 (:status response)))
      (is (= "Error, page not found!" (:body response)))))
  (testing "should return 400 response for GET request to /works/not-a-doi"
    (let [response (app-routes (mock/request :get "/works/not-a-doi"))]
      (is (= 400 (:status response)))
      (is (= "Invalid DOI: not-a-doi" (:body response))))))
