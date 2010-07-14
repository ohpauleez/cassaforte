(defproject cassaforte "1.0.0-SNAPSHOT"
  :description "An extendable Apache Cassandra client for Clojure"
  :dependencies [[org.clojure/clojure "1.2.0-master-SNAPSHOT"]
                 [org.clojure/clojure-contrib "1.2.0-SNAPSHOT"]
                 ;[org.apache.cassandra/cassandra "0.6.3"]
                 ;[org.apache.thrift/thrift "r917130"]]
                 ;[org.apache.thrift/thrift "r962853"]]
                 ;[com.eaio.uuid/uuid "3.1"]
                 ;[cassandra-wrapper-deps "0.6.3-SNAPSHOT"]] ;This works, if we need it, but is more than we need
                 [org.slf4j/slf4j-log4j12 "1.5.8"]
                 ;[log4j "1.2.14"]
                 [org.slf4j/slf4j-api "1.5.8"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [lein-nailgun "0.1.0"]
                     [vimclojure "2.2.0-SNAPSHOT"]
                     [leiningen-run "0.2"]])
