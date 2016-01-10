name := "scaladoc-http"

version := "0.1.0"

// TODO - Try M4 to use https://github.com/scala/scala/pull/4877
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % "2.11.7",
  "org.json4s" %% "json4s-jackson" % "3.3.0"
)