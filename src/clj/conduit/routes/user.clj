(ns conduit.routes.user
  (:require [compojure.api.sweet :refer [context GET PUT]]
            [conduit.db.core :as db]
            [ring.util.http-response :refer :all]
            [ring.util.response :refer [response]]))


(def routes
  (context "/user" []
           :tags ["User"]

           (GET "/" [id]
                :query-params [id :- Long]
                :summary      "Get User"
                (response (db/get-user {:id id})))

           (PUT "/" []
                :return      String
                :summary     "Update user"
                (ok (str "Update user")))))
