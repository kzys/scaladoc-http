package com.pactdisc.scaladoc

import java.nio.file.{StandardCopyOption, Files}

import com.pactdisc.scaladoc.page.{JsonIndex, Index, Template}

import tools.nsc.doc
import tools.nsc.doc.model.DocTemplateEntity

class Factory(universe: doc.Universe, index: doc.Index) extends doc.html.HtmlFactory(universe, index) {
  def copyResource(path: String) {
    val pathInJar = "/scala/tools/nsc/doc/html/resource/" + path
    val src = getClass.getResourceAsStream(pathInJar)
    val dest = siteRoot.toPath.resolve(path)
    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING)
  }

  override def generate() {
    Files.createDirectories(siteRoot.toPath.resolve("lib"))
    libResources foreach (s => copyResource("lib/" + s))

    new Index(universe, index) writeFor this
    new JsonIndex(universe, index) writeFor this

    // writeTemplates(_ writeFor this)
  }

  override def writeTemplates(writeForThis: doc.html.HtmlPage => Unit) {
    val written = collection.mutable.HashSet.empty[DocTemplateEntity]

    def writeTemplate(tpl: DocTemplateEntity) {
      if (!(written contains tpl)) {
        writeForThis(new Template(universe, null, tpl))
        written += tpl
        tpl.templates collect {
          case d: DocTemplateEntity => d
        } map writeTemplate
      }
    }

    writeTemplate(universe.rootPackage)
  }
}