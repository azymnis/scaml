package org.prohax.scaml.output

import scala.xml._
import org.prohax.scaml.ScamlFile

import org.prohax.scaml.models._

object emptyfile extends ScamlFile[Unit] {
  def render(t:Unit) = {
"""
"""
  }
}