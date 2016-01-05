package com.pactdisc.scaladoc
import tools.nsc

object Main {
  def main(args: Array[String]) = {
    nsc.ScalaDoc.main(Array("-usejavacp", "-doc-generator", "com.pactdisc.scaladoc.Doclet", "-d", "build", "src/main/scala/Main.scala"))
  }
}
