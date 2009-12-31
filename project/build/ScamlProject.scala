import sbt._

class ScamlProject(info: ProjectInfo) extends DefaultProject(info) {
  import BasicScalaProject._
  
  val specs = "org.scala-tools.testing" % "specs" % "1.6.0"
}