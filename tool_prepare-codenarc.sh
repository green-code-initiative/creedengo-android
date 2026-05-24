#!/usr/bin/env sh

set -eu

# Define CodeNarc version
codenarcVersion="2.2.5"

# Resolve repository root from this script location so relative paths stay valid.
scriptDir="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
codenarcDir="$scriptDir/codenarc-converter/CodeNarc"
jarPath="$codenarcDir/build/libs/CodeNarc-${codenarcVersion}.jar"

# Build CodeNarc
cd "$codenarcDir"

# CodeNarc's Gradle wrapper (6.9.2) requires Java 11 for this project setup.
if java11Home="$(/usr/libexec/java_home -v 11 2>/dev/null)"; then
	JAVA_HOME="$java11Home" PATH="$java11Home/bin:$PATH" ./gradlew build -x test
else
	echo "Java 11 introuvable. Installe un JDK 11 puis relance ce script." >&2
	exit 1
fi

# Deploy to local repository
cd "$scriptDir/codenarc-converter"
mvn -B install:install-file -Dfile="$jarPath" -DgroupId=org.codenarc -DartifactId=CodeNarc -Dversion="${codenarcVersion}" -Dpackaging=jar

