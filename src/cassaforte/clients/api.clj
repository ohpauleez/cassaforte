(ns cassaforte
  #^{:author "Paul deGrandis"
     :doc "An extendable Apache Cassandra client for Clojure :: API protocols"})
;; TODO a macro to gen the API protocol based on client reflection

;; TODO a macro to extend the gen'd protocol with an auto implementation

;; TODO Look at the Java API implemenation based on what gets reflected

(defprotocol Cassandra-API06
  "A protocol for Cassandra API v 0.6 clients"
  (login [keyspace auth-request])
  (get-col [keyspace k column-path consistency-level])
  (get-slice [keyspace k column-parent predicate consistency-level])
  (multiget-slice [keyspace ks column-parent predicate consistency-level])
  (get-count ^int [keyspace k column-parent consistency-level])
  (get-range-slices [])
  (insert [])
  (batch-mutate [])
  (rm [])
  (describe-keyspace [])
  (describe-keyspaces [])
  (describe-cluster-name [])
  (describe-version [])
  (describe-ring []))
 
