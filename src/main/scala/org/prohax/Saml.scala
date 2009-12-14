package org.prohax

case class Tag(attrs: Map[Symbol, String], tags: Tag*) {
  val name = getClass.getSimpleName

  override def toString = {
    val tagOpen = "<" + name + attrs.map(kv => " " + kv._1.name + "='" + kv._2.replaceAll("'", "\\\\'") + "'").mkString + ">"
    if (tags.isEmpty) {
      tagOpen + "</" + name + ">"
    } else {
      "\n" + tagOpen + tags.map(_.toString).mkString + "</" + name + ">\n"
    }
  }
}

object Samlv1 {
  implicit def stringToTag(s: String) = new span(Map()) {
    override def toString = s
  }

  implicit def pairToMap(p: (Symbol, String)) = Map(p)

  case class div() extends Tag(Map())
  case class html(ts: Tag*) extends Tag(Map(), ts: _*)
  case class head(ts: Tag*) extends Tag(Map(), ts: _*)
  case class body(ts: Tag*) extends Tag(Map(), ts: _*)
  case class title(s: String) extends Tag(Map(), s)
  case class h1(ts: Tag*) extends Tag(Map(), ts: _*)
  case class h2(attr: (Symbol, String), ts: Tag*) extends Tag(Map(attr), ts: _*)
  case class p(attr: Map[Symbol, String], ts: Tag*) extends Tag(attr, ts: _*)
  object p {
    def apply(ts: Tag*): p = apply(Map[Symbol, String](), ts: _*)
  }
  case class span(attr: Map[Symbol, String], ts: span*) extends Tag(attr, ts: _*)
  object span {
    def apply(ts: span*): span = apply(Map[Symbol, String](), ts: _*)

    def apply(a1: (Symbol, String), a2: (Symbol, String), ts: span*): span = apply(Map(a1, a2), ts: _*)

    def apply(a1: (Symbol, String), a2: (Symbol, String), a3: (Symbol, String), ts: span*): span = apply(Map(a1, a2, a3), ts: _*)
  }
}

object Samlv2 {
  trait TagV2 {
    val attrs: Map[Symbol, String]
    val tags: Seq[Tag]
    val name : String
    
    override def toString = {
      val tagOpen = "<" + name + attrs.map(kv => " " + kv._1.name + "='" + kv._2.replaceAll("'", "\\\\'") + "'").mkString + ">"
      if (tags.isEmpty) {
        tagOpen + "</" + name + ">"
      } else {
        "\n" + tagOpen + tags.map(_.toString).mkString + "</" + name + ">\n"
      }
    }
  }
  
  trait NamedByClass {
    val name = getClass.getSimpleName
  }
  
  implicit def stringToTag(s: String) = new Tag(Map()) {
    override def toString = s
  }
  
  case class TagBuilder(n : String) {
    def apply() = new TagV2 {
      val attrs: Map[Symbol, String] = Map()
      val name: String = n
      val tags: Seq[Tag] = Seq()
    }
    
    def apply(ts: Tag*) = new TagV2 {
      val attrs: Map[Symbol, String] = Map()
      val name = n
      val tags: Seq[Tag] = ts
    }

    def apply(a1: (Symbol, String), ts: Tag*) = new TagV2 {
      val attrs: Map[Symbol, String] = Map(a1)
      val name = n
      val tags: Seq[Tag] = ts
    }

    def apply(a1: (Symbol, String), a2: (Symbol, String), ts: Tag*) = new TagV2 {
      val attrs: Map[Symbol, String] = Map(a1, a2)
      val name = n
      val tags: Seq[Tag] = ts
    }

    def apply(a1: (Symbol, String), a2: (Symbol, String), a3: (Symbol, String), ts: Tag*) = new TagV2 {
      val attrs: Map[Symbol, String] = Map(a1, a2, a3)
      val name = n
      val tags: Seq[Tag] = ts
    }
  }

  val List(html, head, title, body, h1, h2, p, span) = List("html", "head", "title", "body", "h1", "h2", "p", "span").map(TagBuilder(_))
  
  implicit def toOldTag(t: TagV2) : Tag = new Tag(t.attrs, t.tags :_ *) {
    override val name = t.name
  }
}
