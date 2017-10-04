name := """taximeter-play"""
organization := "net.kimptoc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies ++= Seq(
  ehcache
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "net.kimptoc.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "net.kimptoc.binders._"

// taxi-meter dependancies
libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "1.3.2",
  "org.apache.commons" % "commons-lang3" % "3.1",
  "joda-time" % "joda-time" % "2.0"
)