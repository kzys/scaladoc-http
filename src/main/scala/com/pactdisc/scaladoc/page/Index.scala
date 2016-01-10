package com.pactdisc.scaladoc.page

import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.HtmlPage
import scala.xml.Elem

/**
  * Created by kazuyoshi on 1/5/16.
  */
class Index(universe: doc.Universe, val index: doc.Index) extends HtmlPage {
  override protected def title = "Index"

  private def createListItem(root: doc.model.Package): Elem = {
    val packages = root.packages.map(entity => {
      createListItem(entity)
    })
    <li><a href={relativeLinkTo(root)}>{root.name}</a><ul>{packages}</ul></li>
  }

  override def body =
    <body>`
      <ul>{universe.rootPackage.packages.map(createListItem(_))}</ul>
    </body>

  override protected def headers =
    <xml:group>
      <link ref="stylesheet" href="/assets/index.css" />
    </xml:group>

  override def path = List("index.html")
}
