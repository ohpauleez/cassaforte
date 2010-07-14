(ns cassaforte
  #^{:author "Paul deGrandis"
     :doc "An extendable Apache Cassandra client for Clojure"}
  (:import
    (org.apache.cassandra.thrift Cassandra$Client ColumnPath)
    (org.apache.thrift.transport TSocket)
    (org.apache.thrift.protocol TBinaryProtocol)))

(defn col
  "A function that creates a correctly formatted ColumnPath object
  from the column family and the column
  Arugments:
    cf - a String, the ColumnFamily
    c - a String, the Column
  Returns
    cp - a ColumnPath Object
  Example:
    (col 'my-column-family' 'my-column')
  "
  ^ColumnPath [^String cf ^String c]
  (let [cp (ColumnPath. cf)]
    (.setColumn cp (.getBytes c)) cp))

(def levels (let [cls (org.apache.cassandra.thrift.ConsistencyLevel/values)
                  words (map #(.toString %) cls)]
              (zipmap (map #(keyword (.toLowerCase %)) words) (map #(org.apache.cassandra.thrift.ConsistencyLevel/valueOf %) words))))

(defn connect-to
  "A function that create a client connected to a Cassandra Thrift interface
  Arguments:
    host - a String, the host you're trying to reach
    port - an int, the port number of the Thrift interface
  Returns:
    client - a Cassandra$Client object
  Example:
    (def raw-client (connect-to 'localhost' 9160))
  "
  ^Cassandra$Client [^String host ^int port]
    (let [tsock (TSocket. host port)
          tprot (TBinaryProtocol. tsock)
          client (do (.open tsock)
                 (Cassandra$Client. tprot))]
      client))

(defmacro proxy-client
  "A macro that returns a function,
  that passes abritrary methods (specificed as Strings) and aruments to a client.
  Arguments:
    host - a String, the host you're trying to reach
    port - an int, the port number of the Thrift interface
  Returns:
     - a function, which you pass Cassandra$Client methods (specified as Strings)
        and arguments to
  Example:
    (def cas (client 'localhost' 9160))
    (cas 'get' 'my-keyspace' 'my-key' (col 'my-column-family' 'my-column') (levels :all))
  NOTES:
    THIS DOES NOT CURRENTLY WORK
  "
  [^String host ^int port]
  `(let [client# (connect-to ~host ~port)]
     (fn [method# & args#]
       (let [m# (.getMethod Cassandra$Client method# (into-array Class (map type args#)))
             a# (into-array Object args#)]
         (println client# m# args#)
         #_(. client# m# a#)
         #_(.m# client# a#)))))

(defmacro client2
  "
  "
  ([]
   (client2 "localhost" 9160))
  ([^String host ^Integer port]
   (client2 (connect-to host port)))
  ([^Cassandra$Client cclient]
  `(fn [method# & args#]
     (let [m# (.getMethod Cassandra$Client method# (into-array Class (map type args#)))
           a# (into-array Object args#)]
       (println ~cclient m# args#)
       #_(. ~cclient m# a#)
       #_(.m# ~cclient a#)))))
