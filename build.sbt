
lazy val `find4car` = (project in file(".")).enablePlugins(PlayScala)
  .settings(
    name := "FIND4CAR",
    version := "1.0",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(guice)
  )
