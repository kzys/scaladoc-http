package com.pactdisc.scaladoc
import tools.nsc.doc
import tools.nsc.doc.html.page
import tools.nsc.doc.model.DocTemplateEntity

class Factory(universe: doc.Universe, index: doc.Index) extends doc.html.HtmlFactory(universe, index) {
  override def generate() {
    new page.Index(universe, index) writeFor this
    new page.IndexScript(universe, index) writeFor this
    if (index.hasDeprecatedMembers)
      new page.DeprecatedIndex(universe, index) writeFor this
    writeTemplates(_ writeFor this)
    for (letter <- index.firstLetterIndex) {
      new page.ReferenceIndex(letter._1, index, universe) writeFor this
    }
  }

  override def writeTemplates(writeForThis: doc.html.HtmlPage => Unit) {
    val written = collection.mutable.HashSet.empty[DocTemplateEntity]

    def writeTemplate(tpl: DocTemplateEntity) {
      if (!(written contains tpl)) {
        writeForThis(new EntityPage(universe, null, tpl))
        written += tpl
        tpl.templates collect {
          case d: DocTemplateEntity => d
        } map writeTemplate
      }
    }

    writeTemplate(universe.rootPackage)
  }
}