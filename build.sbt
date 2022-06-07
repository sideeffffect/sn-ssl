ThisBuild / tlBaseVersion := "0.1"

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Arman Bilge"
ThisBuild / developers += tlGitHubDev("armanbilge", "Arman Bilge")
ThisBuild / startYear := Some(2022)
ThisBuild / tlSonatypeUseLegacyHost := false

ThisBuild / crossScalaVersions := Seq("3.1.2", "2.12.15", "2.13.8")

lazy val root = tlCrossRootProject.aggregate(ssl)

lazy val ssl = project
  .in(file("ssl"))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    name := "sn-ssl"
  )

lazy val tests = crossProject(NativePlatform, JVMPlatform)
  .in(file("tests"))
  .enablePlugins(NoPublishPlugin)
  .nativeEnablePlugins(ScalaNativeJUnitPlugin)
  .settings(
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-s", "-v")
  )
  .jvmSettings(
    libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % Test
  )
  .nativeConfigure(_.dependsOn(ssl))
