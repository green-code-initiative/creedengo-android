# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

### Changed

### Deleted

## [1.2.0] - 2026-01-04

### Added

- [#102](https://github.com/green-code-initiative/ecoCode-android/pull/102) Add rule EC533 (Google ads consent)
- [#105](https://github.com/green-code-initiative/ecoCode-android/pull/105) Add rule EC534 (Tracking Id via `TelephonyManager#getDeviceId()`)
- [#103](https://github.com/green-code-initiative/ecoCode-android/pull/103) Add rule EC549 (Hardware acceleration in Android components, XML)

### Changed
- [#92](https://github.com/green-code-initiative/ecoCode-android/pull/92) Update EC522 (member select) and related tests
- Rework BrightnessOverrideRule checks and clean up unused logic
- The embedded Groovy language analyzer was reconfigured to scan only `.gradle` files since it is the files we are interested in for
  the Android project configuration rules.
  The associated language is named `Groovy (Gradle)` instead of just `Groovy`.
  Since the plugin does not scan `.groovy` files anymore, it can work with a Groovy scanner on the same SonarQube instance.
- Bump embedded CodeNarc to 2.2.5
- Remove unused Groovy/CodeNarc extensions and simplify sensors
- Fix Docker configuration for Apple M1 builds and update the jar path in compose files
- Update CI workflow and documentation (README/RULES)

### Deleted

- [#80](https://github.com/green-code-initiative/ecoCode-android/issues/80) Delete rule EC5002 (SDK range) since it not in
  the [best practices mobile](https://github.com/cnumr/best-practices-mobile) anymore
- [#88](https://github.com/green-code-initiative/ecoCode-android/issues/88) Delete rule EC5003 (Disable obfuscation) since it not in
  the [best practices mobile](https://github.com/cnumr/best-practices-mobile) anymore

## [1.1.0] - 2023-11-17

### Added

- Home page URL for plugins (for display on SonarQube administration page)
- Add `eco-design` tag on all rules
- [#207](https://github.com/green-code-initiative/ecoCode/issues/207) Add release tag analyzis on SonarCloud
- [#58](https://github.com/green-code-initiative/ecoCode-android/pull/58) Add Dark UI theme XML rule
- Support of SonarQube 10.3

### Changed

- Rename repository `ecoCode-mobile` to `ecoCode-android`
- Update the rules naming convention to have the same one as the others ecoCode plugin. Rules identifier now starts by `EC`.
- Update built-in profile names to `ecoCode (Android)`

### Deleted

- Moved iOS plugin to its [own repository](https://github.com/green-code-initiative/ecoCode-ios).

## [1.0.1] - 2023-03-10

### Changed

- Fix plugin iOS release

## [1.0.0] - 2023-03-10

### Added

- [#5](https://github.com/green-code-initiative/ecocode-android/pull/5) Add IOS plugin delivery
- Add `CONTRIBUTING.md`
- Add `CODE_STYLE.md`
- move `INSTALL.md` to common doc in `ecoCode-common` repository

### Changed

- upgrade SonarQube version to 9.9
- [#27](https://github.com/green-code-initiative/ecocode-android/pull/27) preparation for Plugin SonarSource Marketplace integration
- docker / docker-compose upgrade
- update documentation
- upgrade RULES
- [#28](https://github.com/green-code-initiative/ecocode-android/pull/28) Add minSdk and targetSdk
- [#4](https://github.com/green-code-initiative/ecocode-android/pull/4) minSdk and targetSdk properties must be supported too
- [#23](https://github.com/green-code-initiative/ecocode-android/issues/23) Fix CodeNarc issue
- [#20](https://github.com/green-code-initiative/ecocode-android/issues/20) Refactoring `cnumr` to `greencodeinitiative`

## [0.1.0] - 2023-01-03

### Added

- [#13](https://github.com/green-code-initiative/ecocode-android/pull/13) Upgrade some versions + sonar version from 9.3
  to 9.8
- [#14](https://github.com/green-code-initiative/ecocode-android/issues/14) Improve release system

### Changed

- documentation upgrade (internal)
- optimization/refactoring on pom.xml dependencies (internal)
- Fix [WARNING] Maven-shade-plugin overlapping classes and upgrade SonarRuntime.

## [0.0.1] - 2022-12-15

### Added

- First official release of ecocode plugins : android plugin

[unreleased]: https://github.com/green-code-initiative/ecoCode/compare/v1.2.0...HEAD

[1.2.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.2.0

[1.1.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.1.0

[1.0.1]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.0.1

[1.0.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v1.0.0

[0.1.0]: https://github.com/green-code-initiative/ecoCode/releases/tag/v0.1.0

[0.0.1]: https://github.com/green-code-initiative/ecoCode/releases/tag/v0.0.1
