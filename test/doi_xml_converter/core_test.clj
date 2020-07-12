(ns doi-xml-converter.core-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.core :refer :all]
            [ring.mock.request :as mock]))

(deftest user-journey-tests
  (testing "should return 404 response for GET request to an invalid endpoint"
    (let [response (app-routes (mock/request :get "/not-an-endpoint"))]
      (is (= 404 (:status response)))
      (is (= "Error, page not found!" (:body response)))))
  (testing "should return 400 response for GET request with an invalid DOI"
    (let [response (app-routes (mock/request :get "/works/not-a-doi"))]
      (is (= 400 (:status response)))
      (is (= "Invalid DOI: not-a-doi" (:body response)))))
  (testing "should return 404 response for GET request to a valid DOI that does not exist"
    (let [response (app-routes (mock/request :get "/works/10.5555/11111111"))]
      (is (= 404 (:status response)))
      (is (= "DOI 10.5555/11111111 does not exist" (:body response))))))
