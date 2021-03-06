
//SBT Plugin for assembly

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.2.0" )
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")
addSbtPlugin("io.spray" %% "sbt-revolver" % "0.7.2")
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.0.2")
