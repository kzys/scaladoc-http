package com.pactdisc.scaladoc

import scala.tools.nsc.doc
import scala.tools.nsc.doc.html.HtmlPage
import scalatags.Text.{all => tags} // because HtmlPage has "body"
import scalatags.Text.all._

/**
  * Created by kazuyoshi on 1/5/16.
  */
class IndexPage(universe: doc.Universe, val index: doc.Index) extends HtmlPage {
  override protected def title = "Index"

  override def body = Tag.unparsed(tags.body())

  override protected def headers =
    Tag.group(link(href := relativeLinkTo(List("index.css", "lib"))))

  override def path = List("index.html")
}
