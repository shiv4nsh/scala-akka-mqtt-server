
name := "scala-akka-mqtt-server"

version := "1.0"

scalaVersion := "2.11.7"

organization := "com.knoldus"

libraryDependencies ++=Seq( 
 "com.typesafe.akka" %% "akka-actor" % "2.4.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.0",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.0",
 "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0-M2",
 "org.scodec" %% "scodec-core" % "1.7.1",
  "org.scodec" %% "scodec-bits" % "1.0.9",
  "org.scalaz" %% "scalaz-core" % "7.1.3")

fork in run := true
