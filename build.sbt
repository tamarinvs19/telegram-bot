version in Global := "0.1"

scalaVersion in Global := "2.13.5"

name := "telegram-bot"

libraryDependencies in Global ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.5" % "test",
  "org.augustjune" %% "canoe" % "0.5.1"
)

scalacOptions ++= Seq (
  "-Ywarn-unused"
)

inThisBuild(
  List(
    scalaVersion := "2.13.5",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)


lazy val telegramBotProject = project.in(file("."))
  .settings(
    name := "telegram-bot"
  ).aggregate(
  dataBaseProject,
  interfaceProject,
  receiverProject,
  userProject,
  storageClassesProject
)


lazy val dataBaseProject = Project(id = "dataBase", base = file("dataBase"))
  .settings(
    name := "dataBase"
  )
  .dependsOn(
    storageClassesProject,
    userProject
  )


lazy val interfaceProject = Project(id = "interface", base = file("interface"))
  .settings(
    name := "interface"
  )
  .dependsOn(
    storageClassesProject,
    userProject
  )


lazy val receiverProject = Project(id = "receiver", base = file("receiver"))
  .settings(
    name := "receiver"
  )
  .dependsOn(
    storageClassesProject
  )


lazy val userProject = Project(id = "user", base = file("user"))
  .settings(
    name := "user"
  )


lazy val storageClassesProject = Project(id = "storageClasses", base = file("storageClasses"))
  .settings(
    name := "storageClasses"
  )

