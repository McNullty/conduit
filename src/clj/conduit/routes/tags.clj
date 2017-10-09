(ns conduit.routes.tags
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST DELETE resource]]
            [conduit.db.core :refer [*db*] :as db]
            [clojure.java.jdbc :as jdbc]))

(def routes
  (context "/tags" []
           :tags ["Tags"]

           (GET "/" []
                :return       {:tags [String]}
                :summary      "Get all tags"
                (ok {:tags (vec (map :name (db/get-tags)))}))))
