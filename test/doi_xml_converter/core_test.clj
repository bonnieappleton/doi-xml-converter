(ns doi-xml-converter.core-test
  (:require [clojure.test :refer :all]
            [doi-xml-converter.core :refer :all]
            [ring.mock.request :as mock]))

(deftest a-test
  (testing "should return 404 response for GET request to /not-an-endpoint"
    (let [response (app-routes (mock/request :get "/not-an-endpoint"))]
      (is (= (:status response) 404)))))
