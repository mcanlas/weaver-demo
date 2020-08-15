package com.htmlism.weaverdemo

import weaver.SimpleIOSuite
import cats.effect._

object DemoIOSuite extends SimpleIOSuite {

  pureTest("hello pure"){
    expect("hello".size == 6)
  }

  private val random = IO(java.util.UUID.randomUUID())

  // A test for side-effecting functions
  simpleTest("hello side-effects") {
    for {
      x <- random
      y <- random
    } yield expect(x != y)
  }

  // A test with logs
  loggedTest("hello logs"){ log =>
    for {
      x <- random
      _ <- log.info(s"x : $x")
      y <- random
      _ <- log.info(s"y : $y")
    } yield expect(x != y)
  }

}