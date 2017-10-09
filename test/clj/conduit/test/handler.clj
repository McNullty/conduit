(ns conduit.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [conduit.handler :refer :all]))

(deftest test-app
  (testing "not-found route"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))

(deftest users-routes
  (testing "get user"
    (let [response ((app) (request :get "/user"))]
      (is (= 404 (:status response))))))
