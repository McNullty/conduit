(ns conduit.middleware
  (:require [conduit.env :refer [defaults]]
            [conduit.config :refer [env]]
            [ring.middleware.flash :refer [wrap-flash]]
            [immutant.web.middleware :refer [wrap-session]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.accessrules :refer [restrict]]
            [buddy.auth :refer [authenticated?]]
            [buddy.auth.backends.token :refer [jwe-backend]]
            [buddy.sign.jwt :refer [encrypt]]
            [buddy.core.nonce :refer [random-bytes]]
            [clj-time.core :refer [plus now minutes]]))

(defn on-error [request response]
  {:status 403
   :headers {}
   :body (str "Access to " (:uri request) " is not authorized")})

(defn wrap-restricted [handler]
  (restrict handler {:handler authenticated?
                     :on-error on-error}))

(def secret (random-bytes 32))

(def token-backend
  (jwe-backend {:secret secret
                :options {:alg :a256kw
                          :enc :a128gcm}}))

(defn token [username]
  (let [claims {:user (keyword username)
                :exp (plus (now) (minutes 60))}]
    (encrypt claims secret {:alg :a256kw :enc :a128gcm})))

(defn wrap-auth [handler]
  (let [backend token-backend]
    (-> handler
        (wrap-authentication backend)
        (wrap-authorization backend))))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-auth
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))))
