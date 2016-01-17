package com.pactdisc.scaladoc.page

import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.{Page, HtmlFactory}
import scala.util.parsing.json.{JSONArray, JSONObject}

/**
  * Created by kazuyoshi on 1/5/16.
  */
class JsonIndex(universe: doc.Universe, val index: doc.Index) extends Page {
  private def listPackage(root: doc.model.Package): Seq[JSONObject] = {
    val obj = Map("name" -> root.name, "path" -> root.qualifiedName)
    List(JSONObject(obj))
  }

  override def writeFor(site: HtmlFactory) {
    val packages = universe.rootPackage.packages.flatMap(listPackage)
    writeFile(site) {
      _.write(JSONArray(packages).toString())
    }
  }

  override def path = List("index.json")
}
