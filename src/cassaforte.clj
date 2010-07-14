(ns cassaforte
  "An extendable Apache Cassandra client for Clojure"
  (:import
    (org.apache.cassandra.thrift Cassandra$Client ColumnPath)
    (org.apache.thrift.transport TSocket)
    (org.apache.thrift.protocol TBinaryProtocol)))

(defn col [cf c] (let [cp (ColumnPath. cf)]
                (.setColumn cp (.getBytes c)) cp))
(def cl-all org.apache.cassandra.thrift.ConsistencyLevel/ALL)

(defn connect-to
  "
  Connect to a Cassandra Thrift interface
  "
  (^Cassandra$Client [^String host ^int port]
    (let [tsock (TSocket. host port)
          tprot (TBinaryProtocol. tsock)
          client (do (.open tsock)
                 (Cassandra$Client. tprot))]
      client)))

(defmacro client
  "
  "
  [^String host ^int port]
  `(let [client# (connect-to ~host ~port)]
     (fn [method# & args#]
       (let [m# (.getMethod Cassandra$Client method# (into-array Class (map type args#)))
             a# (into-array Object args#)]
         (println client# m# args#)
         #_(. client# m# a#)
         #_(.m# client# a#)))))

