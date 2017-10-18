(ns conduit.routes.tags
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST DELETE resource]]
            [conduit.db.core :refer [*db*] :as db]
            [clojure.spec.alpha :as s]))

(s/def ::tags (s/coll-of string?))

(def routes
  (context "/tags" []
           :tags ["Tags"]

           (GET "/" []
                :responses {200 {:schema (s/keys :req-un [::tags])}}
                :summary      "Get all tags"
                (ok {:tags (vec (map :name (db/get-tags)))}))))
