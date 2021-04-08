version in Global := "0.1"

scalaVersion in Global := "2.13.5"

name := "telegram-bot"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.5" % "test",
  "org.augustjune" %% "canoe" % "0.5.1"
)

lazy val main = project.in(file("."))

inThisBuild(
  List(
    scalaVersion := "2.13.5",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)
