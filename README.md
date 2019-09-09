# OS Detector Plugin for SBT
A SBT plugin that detects the OS name and architecture, providing a uniform
classifier to be used in the names of native artifacts.

It uses [os-maven-plugin](https://github.com/trustin/os-maven-plugin) under the
hood thus produces the same result.

Requires Java 8 or up.

## Usage
To use this plugin, include in your plugins.sbt:

```scala
addSbtPlugin("io.phdata" % "sbt-os-detector" % "0.1" classifier "assembly")
```

The plugin creates ``osDetector*`` settings in your project, through which you
can access the following attributes:
- ``osDetectorOs``: normalized OS name
- ``osDetectorArch``: architecture
- ``osDetectorClassifier``: classifier, which is ``osdetector.os + '-' + osdetector.arch``, e.g., ``linux-x86_64``
- ``osDetectorRelease``: only available if ``osDetectorOs`` is ``linux``.
  ``null`` on non-linux systems. It provides additional information about the
  linux release:
 - ``id``: the ID for the linux release
 - ``version``: the version ID for this linux release
 - ``isLike(baseRelease)``: ``true`` if this release is a variant of the given
   base release. For example, ubuntu is a variant of debian, so on a debian or
   ubuntu system ``isLike('debian`)`` returns ``true``.

### Typical usage example
### To add platform specific depencencies

```scala
libraryDependencies += "group.id" % "artifact" % "version" % "test" classifier osDetectorClassifier.value
```

## To build and install locally
```
$ git clone git@github.com:phdata/sbt-os-detector.git
$ cd sbt-os-detector
$ sbt publishLocal
```