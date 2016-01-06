package com.pactdisc.scaladoc
import tools.nsc.doc.Universe
import tools.nsc.doc.html.page.Template
import tools.nsc.doc.html.page.diagram.DiagramGenerator
import tools.nsc.doc.model.DocTemplateEntity

class EntityPage(universe: Universe, diagramGenerator: DiagramGenerator, entity: DocTemplateEntity) extends Template(universe, diagramGenerator, entity) {
    override val headers =
      <xml:group>
        <link href={ relativeLinkTo{List("template.css", "lib")} } media="screen" type="text/css" rel="stylesheet"/>
      </xml:group>

}
