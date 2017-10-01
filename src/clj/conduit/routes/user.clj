(ns conduit.routes.user
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET PUT]]))

(def routes
  (context "/user" []
           :tags ["User"]

           (GET "/" []
                :return       String
                :summary      "Get User"
                (ok (str "Get user")))

           (PUT "/" []
                :return      String
                :summary     "Update user"
                (ok (str "Update user")))))
