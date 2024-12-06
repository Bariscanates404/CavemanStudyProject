(ns example.system
  (:require [example.routes :as routes]
            [next.jdbc.connection :as connection]
            [ring.adapter.jetty :as jetty])
  (:import (com.zaxxer.hikari HikariDataSource)             ;; problem 1
           (org.eclipse.jetty.server Server)))


(defn start-server
  [system]
  (jetty/run-jetty
    (partial #'routes/root-handler system)
    {:port  9999
     :join? false}))

(set! *warn-on-reflection* true)

(defn start-db
  []
  (connection/->pool HikariDataSource                       ;; problem 2
                     {:dbtype   "postgres"
                      :dbname   "postgres"
                      :username "postgres"
                      :password "postgres"
                      :port "5632"}))

(defn stop-db
  [db]
  (HikariDataSource/.close db))                             ;; problem 3

(defn stop-server
  [server]
  (Server/.stop server))

(defn start-system
  []
  (let [system-so-far {::db (start-db)}]
    (merge system-so-far {::server (start-server system-so-far)})))

(defn stop-system
  [system]
  (stop-server (::server system))
  (stop-db (::db system)))

