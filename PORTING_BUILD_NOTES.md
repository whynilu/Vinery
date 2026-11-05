# Minecraft 26.3 Fabric port — build status

This branch is an isolated port target. The original Minecraft 1.21.1 line remains untouched.

## Build setup currently present

- Gradle multi-project build: `common` + `fabric`.
- Minecraft `26.3`, Fabric Loader `0.20.1`, Fabric API `0.165.2+26.3`.
- Java 25 toolchain and Architectury's non-remapping Loom plugin.
- Fabric entrypoints, client entrypoint, platform helper implementation, REI plugin, mixins, resources, and generated worldgen data are present in the source tree.
- GitHub Actions workflow `.github/workflows/port-build-26.3.yml` builds `:fabric:build` on pushes to this branch and uploads the resulting JAR.

## Known migration risks

- The Foundation dependency still points at the 1.21.1 Maven branch/version and must be replaced or ported for 26.3.
- JEI/EMI integrations are excluded from the common source set; REI and other optional dependencies still need version/runtime validation.
- Minecraft 26.3 API changes may produce compiler errors across common and Fabric code. Resolve those from the actual CI compiler output rather than guessing at signatures.
- The local execution environment used during preparation does not have Java 25 or a verified Gradle dependency cache. The Java 25 GitHub Actions runner is the authoritative build environment.

## Current status

The port is not yet considered buildable or playable until the branch workflow succeeds and the produced artifact is tested in a Minecraft 26.3 Fabric instance. A successful compile alone is not a runtime test.
