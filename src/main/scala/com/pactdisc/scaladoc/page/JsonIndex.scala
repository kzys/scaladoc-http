package com.pactdisc.scaladoc.page

import scala.collection.mutable
import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.{Page, HtmlFactory}
import scala.tools.nsc.doc.model.{DocTemplateEntity, MemberEntity}
import scala.util.parsing.json.{JSONArray, JSONObject}

/**
  * Created by kazuyoshi on 1/5/16.
  */
class JsonIndex(universe: doc.Universe, val index: doc.Index) extends Page {
  private def collectSymbolsFromTemplate(symbols: mutable.Map[String, List[String]], template: DocTemplateEntity): Unit = {
    for (method <- template.methods) {
      val xs = symbols.getOrElse(method.name, List.empty)
      val qualifiedName = s"${template.qualifiedName} ${method.signature}"
      symbols.update(method.name, qualifiedName :: xs)
    }
  }

  private def collectSymbols(symbols: mutable.Map[String, List[String]], root: doc.model.Package): Unit = {
    for (template <- root.templates) {
      template match {
        case entity: DocTemplateEntity => collectSymbolsFromTemplate(symbols, entity)
        case _ => ;
      }

      val xs = symbols.getOrElse(template.name, List.empty)
      val qualifiedName = if (template.isObject) {
        template.qualifiedName + " object"
      } else {
        template.qualifiedName
      }

      symbols.update(template.name, qualifiedName :: xs)
    }

    for (pkg <- root.packages) {
      val xs = symbols.getOrElse(pkg.name, List.empty)
      symbols.update(pkg.name, pkg.qualifiedName :: xs)
      collectSymbols(symbols, pkg)
    }
  }

  override def writeFor(site: HtmlFactory) {
    val symbols = mutable.Map.empty[String, List[String]]
    collectSymbols(symbols, universe.rootPackage)

    val newMap = symbols.map({
      case (key, value) => key -> JSONArray(value)
    }).toMap

    writeFile(site) { _.write(JSONObject(newMap).toString()) }
  }

  override def path = List("index.json")
}
