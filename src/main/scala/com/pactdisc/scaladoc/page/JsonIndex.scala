package com.pactdisc.scaladoc.page

import scala.collection.mutable
import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.{Page, HtmlFactory}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

/**
  * Created by kazuyoshi on 1/5/16.
  */
class JsonIndex(universe: doc.Universe, val index: doc.Index) extends Page {
  private def listPackage(root: doc.model.Package): Seq[JObject] = {
    val obj = ("name" -> root.name) ~ ("path" -> root.qualifiedName)
    obj +: root.packages.flatMap((pkg) => {
      listPackage(pkg)
    })
  }
  override def writeFor(site: HtmlFactory) {
    val packages = universe.rootPackage.packages.flatMap(listPackage)
    writeFile(site) {
      _.write(compact(render(packages)))
    }
  }

  override def path = List("index.json")
}
