(ns user
  (:require [luminus-migrations.core :as migrations]
            [conduit.config :refer [env]]
            [mount.core :as mount]
            conduit.core))

(defn start []
  (mount/start-without #'conduit.core/repl-server))

(defn stop []
  (mount/stop-except #'conduit.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))


