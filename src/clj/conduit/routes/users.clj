(ns conduit.routes.users
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context GET POST DELETE resource]]))

(def routes
  (context "/users" []
           :tags ["User Auth"]

           (GET "/" []
                :return       string?
                :summary      "Login"
                (ok (str "Logged in")))

           (POST "/" []
                 :return      string?
                 :summary     "Register"
                 (ok (str "Thank you for registering")))))
