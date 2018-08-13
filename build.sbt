val legacyScalaVersions = Seq("2.11.12")
val newScalaVersions = Seq("2.12.6")
val allScalaVersions = newScalaVersions ++ legacyScalaVersions

val allScala = Seq(crossScalaVersions := allScalaVersions)
val legacyScala = Seq(crossScalaVersions := legacyScalaVersions)
val newScala = Seq(crossScalaVersions := newScalaVersions)

lazy val `shared-lib` = (project in file("shared-lib"))
  .settings(allScala :_*)

lazy val `legacy-app` = (project in file("legacy-app"))
  .dependsOn(`shared-lib`)
  .settings(legacyScala :_*)

lazy val `modern-app` = (project in file("modern-app"))
  .dependsOn(`shared-lib`)
  .settings(newScala :_*)

lazy val root = (project in file("."))
  .settings(allScala :_*)
  .aggregate(`shared-lib`, `legacy-app`, `modern-app`)
