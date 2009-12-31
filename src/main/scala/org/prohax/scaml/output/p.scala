package org.prohax.scaml.output

import scala.xml._
import org.prohax.scaml.ScamlFile

import org.prohax.scaml.models._
import org.prohax.scaml.dsl._
import org.prohax.scaml.dsl.Helpers._

object p extends ScamlFile[Unit] {
  def render(t:Unit) = {
"""
  <p/>
"""
  }
}