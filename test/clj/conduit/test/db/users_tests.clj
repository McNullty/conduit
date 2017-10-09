(ns conduit.test.db.users_tests
  (:require [conduit.db.core :refer [*db*] :as db]
            [luminus-migrations.core :as migrations]
            [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]
            [conduit.config :refer [env]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'conduit.config/env
     #'conduit.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-create-and-read
  (jdbc/with-db-transaction [t-conn *db*]
    (jdbc/db-set-rollback-only! t-conn)
    (let [input {:username "alice"
                 :email  "alice@wonderland.com"
                 :token "hashvalue"
                 :image "www.imgur.com/myimage"
                 :bio "I like mushrooms"}
          id (first (db/create-user! t-conn input))
          expected (merge input id)
          actual (db/get-user t-conn id)]
      (is (contains? id :id))
      (is (contains? expected :id))
      (is (= expected actual)))))
