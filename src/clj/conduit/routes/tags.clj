(ns conduit.routes.tags
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST DELETE resource]]))

(def routes
  (context "/tags" []
           :tags ["Tags"]

           (GET "/" []
                :return       {:tags [String]}
                :summary      "Get all tags"
                (ok {:tags ["reactjs",
                            "angularjs"]}))))
