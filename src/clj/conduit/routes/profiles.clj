(ns conduit.routes.profiles
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST]]))

(def routes
  (context "/profiles" []
           :tags ["Profiles"]

           (GET "/:username" []
                :summary "Get profile"
                (ok (str "Get profile")))

           (POST "/:username" []
                 (ok (str "Follow user")))))
