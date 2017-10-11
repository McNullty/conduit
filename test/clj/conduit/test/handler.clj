(ns conduit.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [conduit.handler :refer :all]
            [cheshire.core :as cheshire]
            [conduit.db.core :refer [*db*] :as db]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(use-fixtures
  :once
  (fn [f]
    (db/create-tag! {:name "reactjs"})
    (db/create-tag! {:name "angularjs"})
    (f)
    (db/delete-all-tags!)))

(deftest test-app
  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 404 (:status response)))))

  (testing "tags api"
    (let [response ((app) (request :get "/api/tags"))
          body     (parse-body (:body response))]
      (is (= 200 (:status response)))
      (is (= ["reactjs","angularjs"] (:tags body))))))
