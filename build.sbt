/*
 * Copyright 2019 phData Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

lazy val root = (project in file(".")).
  settings(
    name := "sbt-os-detector",
    organization := "io.phdata",
    version := "0.1",
    scalaVersion := "2.12.8",
    sbtPlugin := true,
    sbtVersion := "1.2.8"
  )

libraryDependencies ++= Seq(
  "com.google.gradle" % "osdetector-gradle-plugin" % "1.6.2",
  // os-maven-plugin is a dependency of osdetector-gradle-plugin, but SBT won't add it to the classpath because the
  // pom specifies the packaging as `maven-plugin` instead of jar.
  "kr.motd.maven" % "os-maven-plugin" % "1.6.2"
    artifacts (Artifact("os-maven-plugin", "jar", "jar"))
    exclude("org.apache.maven", "maven-plugin-api")
    exclude("org.codehaus.plexus", "plexus-utils")
)

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("kr.motd.maven.**" -> "io.phdata.shaded.kr.motd.maven.@1").inAll,
  ShadeRule.rename("com.google.gradle.**" -> "io.phdata.shaded.com.google.gradle.@1").inAll,
  ShadeRule.rename("javax.**" -> "io.phdata.shaded.javax.@1").inAll
)

artifact in(Compile, assembly) := {
  val art = (artifact in(Compile, assembly)).value
  art.withClassifier(Some("assembled"))
}

addArtifact(artifact in(Compile, assembly), assembly)
