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
    version := "0.3.0-SNAPSHOT",
    scalaVersion := "2.12.8",
    sbtPlugin := true,
    sbtVersion := "1.2.8"
  )

libraryDependencies ++= Seq(
  "com.google.gradle" % "osdetector-gradle-plugin" % "1.6.2",
)

resolvers ++= Seq(
  "phData Snapshot Repo" at "https://repository.phdata.io/artifactory/libs-snapshot",
  "phData Release Repo" at "https://repository.phdata.io/artifactory/libs-release")


credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

val artifactoryUrl = "https://repository.phdata.io/artifactory"

publishTo := {
  if (version.value.endsWith("SNAPSHOT"))
    Some("phData Snapshots".at(s"$artifactoryUrl/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime))
  else
    Some("phData Releases".at(s"$artifactoryUrl/libs-release-local"))
}
