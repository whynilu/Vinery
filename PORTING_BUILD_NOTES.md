# 26.3 build reconstruction notes

This first build-setup pass restores the missing Gradle entry points and switches the project toward the Minecraft 26.3 non-remapping Architectury Loom toolchain.

## Added
- `settings.gradle`: plugin repositories and the `common` + `fabric` projects only.
- `gradle.properties`: Minecraft 26.3, Fabric Loader 0.20.1, supplied Fabric API 0.165.2+26.3, Java-era project metadata, and Architectury API 22.0.5.
- `fabric/build.gradle`: Fabric module wiring, common module dependency, and Fabric runtime dependencies.

## Still unresolved
- The root `build.gradle` and `common/build.gradle` still need a coordinated migration and validation.
- Foundation 1.0.7 and optional JEI/REI/EMI versions are temporary legacy references, not assertions of 26.3 compatibility.
- The Fabric client entrypoint, Fabric villager registration class, and `PlatformHelperImpl` are absent from the supplied source archive.
- Shadow/production task wiring may need adjustment after configuration against Loom 1.19.
- No build has been run: this environment has Java 21, not Java 25, and cannot download Gradle dependencies.
