version in Global := "0.1"

scalaVersion in Global := "2.13.5"

name := "telegram-bot"

libraryDependencies in Global ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.1.0",
  "org.scalatest" %% "scalatest" % "3.2.8" % "test",
  "com.github.nscala-time" %% "nscala-time" % "2.28.0"
)

inThisBuild(
  List(
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions ++= Seq (
      "-Ywarn-unused"
    )
  )
)

lazy val main = project.in(file("."))
