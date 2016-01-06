package com.pactdisc.scaladoc

import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.HtmlPage
import scala.xml.NodeSeq

/**
  * Created by kazuyoshi on 1/5/16.
  */
class IndexPage(universe: doc.Universe, val index: doc.Index) extends HtmlPage {
  override protected def title = "Index"

  override def body: NodeSeq = <body></body>

  override protected def headers: NodeSeq =
    <xml:group>
      <link href={ relativeLinkTo{List("index.css", "lib")} }  media="screen" type="text/css" rel="stylesheet"/>
    </xml:group>


  override def path = List("index.html")
}
