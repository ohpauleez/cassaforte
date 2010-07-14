(ns cassaforte.core
  (:use [cassaforte :only cassandra])
  (:import
    (org.apache.cassandra.thrift
      ConsistencyLevel Cassandra$Client
      ColumnParent ColumnPath
      SlicePredicate SliceRange KeyRange)
    (org.apache.thrift.transport TSocket)
    (org.apache.thrift.protocol TBinaryProtocol)))

