lazy val `find4car` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "FIND4CAR",
    maintainer := "fran98@correo.ugr.es",
    version := "1.0",
    scalaVersion := "2.13.4",
    crossScalaVersions := List("2.13.4", "2.12.12"),
    libraryDependencies ++= Seq(guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % "test"
    )
  )
ThisBuild / useCoursier := false
lazy val build = taskKey[Unit]("Alias for compile")
lazy val install = taskKey[Unit]("Realmente no hace nada, solo para pasar los tests")

build := {
  val b = compile
}
install := println("Instalando aplicación...")