package com.pactdisc.scaladoc
import tools.nsc
import tools.nsc.doc.doclet._

class Doclet extends Generator with Universer with Indexer {
  def generateImpl() {
    new Factory(universe, index).generate()
  }
}
