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

package io.phdata.sbt

import com.google.gradle.osdetector.OsDetector
import sbt.{AutoPlugin, _}

object OsDetectorPlugin extends AutoPlugin {

  object autoImport {
    val osDetectorOs = settingKey[String]("Detected OS")
    val osDetectorArch = settingKey[String]("Detected architecture")
    val osDetectorClassifier = settingKey[String]("Detected OS and Arch classifier")
    val osDetectorRelease = settingKey[String]("Detected OS release number")
  }

  import autoImport._

//  override lazy val buildSettings = Seq(
//    osDetectorOs := "foo",
//    osDetectorArch := "bar",
//    osDetectorClassifier := "foo-bar",
//    osDetectorRelease := ""
//  )
  lazy val d = new OsDetector()
  override lazy val buildSettings = Seq(
    osDetectorOs := d.getOs,
    osDetectorArch := d.getArch,
    osDetectorClassifier := d.getClassifier,
    osDetectorRelease := Option(d.getRelease).map(_.getVersion).getOrElse("")
  )
}


