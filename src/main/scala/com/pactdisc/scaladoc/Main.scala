package com.pactdisc.scaladoc

import java.nio.file.{Path, Paths, Files}
import java.util.function.Predicate
import java.util.stream.Collectors
import collection.JavaConverters._

import tools.nsc

object Main {
  def listFiles(root: String): Iterable[Path] = {
    val predicate = new Predicate[Path] {
      override def test(path: Path) = {
        path.toString.endsWith(".scala") || path.toString.endsWith(".java")
      }
    }
    Files.walk(Paths.get(root)).filter(predicate).collect(Collectors.toList()).asScala
  }

  def main(args: Array[String]) {
    val files = args.flatMap(listFiles).map(_.toString)
    nsc.ScalaDoc.main(Array(
      "-usejavacp",
      "-doc-generator", "com.pactdisc.scaladoc.Doclet",
      "-d", "build"
    ) ++ files)
  }
}
