package com.htmlism.weaverdemo

import weaver.IOSuite
import cats.effect._

// Same as SimpleIOSuite, but supports sharing a resource across tests
object DemoResourceSuite extends IOSuite {
  type Res = Int

  def sharedResource : Resource[IO, Int] = Resource
    .make(
      IO(println("Making resource"))
        .as(123)
    )(n => IO(println(s"Closing resource $n")))

  test("resource not visible"){
    expect(123 == 123)
  }

  test("test with resource"){ n =>
    expect(n == 123)
  }

  test("test with resource and a logger"){ (n, log) =>
    log.info("log was available")
    expect(n == 123)
  }
}