lazy val `weaver-demo` =
  project
    .in(file("."))
    .withEffectMonad
    .withTesting
    .withTestingEffect
