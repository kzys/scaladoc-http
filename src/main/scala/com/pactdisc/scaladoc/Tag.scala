package com.pactdisc.scaladoc

import scala.xml.{Group, Unparsed}
import scalatags.Text.all._

/**
  * Created by kazuyoshi on 1/5/16.
  */
object Tag {
  def unparsed(tag: Tag) = Unparsed(tag.toString())
  def group(tag: Tag) = Group(unparsed(tag))
}
