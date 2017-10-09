(ns conduit.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [conduit.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 404 (:status response)))))

  (testing "tags api"
    (let [response ((app) (request :get "/api/tags"))]
      (is (= 200 (:status response))))))
