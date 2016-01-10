package com.pactdisc.scaladoc.page

import scala.tools.nsc.doc.Universe
import scala.tools.nsc.doc.html.HtmlPage
import scala.tools.nsc.doc.html.page
import scala.tools.nsc.doc.html.page.diagram.DiagramGenerator
import scala.tools.nsc.doc.model.DocTemplateEntity

class Template(universe: Universe, diagramGenerator: DiagramGenerator, entity: DocTemplateEntity) extends HtmlPage {
  // Since "body" is defined as val in Template, inheriting from the class doesn't work
  val original = new page.Template(universe, diagramGenerator, entity)

  override val headers =
    <xml:group>
      <link rel="stylesheet" href="/assets/template.css" />
    </xml:group>

  override def body =
    <xml:group>
      <div id="sidebar"></div>
      <div id="body">{original.body}</div>
      <script type="text/javascript" src="/assets/template.js"></script>
    </xml:group>

  override protected def title = original.title
  override def path = original.path
}
