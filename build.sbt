version in Global := "0.1"

scalaVersion in Global := "2.13.5"

name := "telegram-bot"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies in Global ++= Seq(
  "com.softwaremill.sttp.client3" %% "core" % "3.3.0",  // http client
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % "3.3.0", // for cats-effect 3.x
  "com.bot4s" %% "telegram-core" % "5.0.0-0e30d39a-SNAPSHOT",                // telegram api
  "org.typelevel" %% "cats-effect" % "3.1.0",
  "org.scalatest" %% "scalatest" % "3.2.8" % "test",
  "com.github.nscala-time" %% "nscala-time" % "2.26.0"
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
