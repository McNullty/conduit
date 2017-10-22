(ns conduit.routes.tags
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST DELETE resource]]
            [conduit.db.core :refer [*db*] :as db]
            [clojure.spec.alpha :as s]))

(s/def ::tags (s/coll-of string?))
(s/def ::body (s/coll-of string?))
(s/def ::errors (s/keys :req-un [::body]))

(def routes
  (context "/tags" []
           :tags ["Tags"]

           (GET "/" []
                :responses {200 {:description "OK"
                                 :schema (s/keys :req-un [::tags])}
                            422 {:description "Unexpected error"
                                 :schema (s/keys :req-un [::errors])}}
                :summary "Get tags"
                :description "Get tags. Auth not required"
                (ok {:tags (vec (map :name (db/get-tags)))}))))
