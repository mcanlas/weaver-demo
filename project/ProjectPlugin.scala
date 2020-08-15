import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {
  override def trigger = allRequirements

  override lazy val projectSettings = Seq(
    scalaVersion := "2.13.3"
  )

  object autoImport {

    implicit class ProjectOps(p: Project) {
      def withEffectMonad: Project =
        p.settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.4")

      def withTesting: Project =
        p.settings(
          libraryDependencies += "com.disneystreaming" %% "weaver-framework" % "0.4.2" % Test,
          testFrameworks += new TestFramework("weaver.framework.TestFramework")
        )
    }

  }

}
