(ns conduit.routes.user
  (:require [compojure.api.sweet :refer [context GET PUT resource]]
            [conduit.db.core :as db]
            [ring.util.http-response :refer :all]
            [ring.util.response :refer [response]]))


(def routes
  (context "/user" []
           :tags ["User"]

           (resource
             {:get {:parameters {:query-params {:id int?}}
                    :summary      "Get User"
                    :handler (fn [id] (ok (db/get-user {:id id})))}})

           (PUT "/" []
                :return      string?
                :summary     "Update user"
                (ok (str "Update user")))))
