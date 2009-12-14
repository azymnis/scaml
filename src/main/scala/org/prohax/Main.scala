package org.prohax

import collection.mutable.{HashMap, Map}
import java.lang.String

case class Post(username: String, title: String, body: String)

object Main {
  def main(args: Array[String]) {
    val post = Post("glen", "opinions", "wack.")
    //    println(render(post))
    println(render2(post))
  }

  def render2(post: Post) = {
    import Samlv2._
    html(
      head(
        title(post.title)
        ),
      body(
        h1(post.title, p("foo")),
        h2('class -> "us'er", post.username),
        p('class -> "post", post.body),
        span("span1"),
        span('class -> "span2", "span2"),
        span('class -> "span3", 'id -> "win", "span3"),
        span('class -> "span3", 'id -> "winboat", 'style -> "lol: strong", "span3")
        ))
  }

  def render(post: Post) = {
    import Samlv1._
    html(
      head(
        title(post.title)
        ),
      body(
        h1(post.title, p("foo")),
        h2('class -> "us'er", post.username),
        p('class -> "post", post.body),
        span("span1"),
        span('class -> "span2", "span2"),
        span('class -> "span3", 'id -> "win", "span3"),
        span('class -> "span3", 'id -> "winboat", 'style -> "lol: strong", "span3")
        )
      )
  }

}