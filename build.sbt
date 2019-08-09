name := "tp-spark-idoughi"

version := "0.1"

scalaVersion := "2.12.8"

conflictManager := ConflictManager.latestRevision

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.4.0" % "provided"