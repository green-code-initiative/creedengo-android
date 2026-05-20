# Build & Deploy the Plugin to SonarQube (Local Docker)

Complete guide to build the creedengo-android project and deploy the plugin to a local SonarQube instance via Docker.

## Prerequisites

- **Java 11** (JDK) — required by the project
- **Maven 3.8+**
- **Gradle 6.9.2** — needed to build CodeNarc
- **Docker** and **Docker Compose**

Verify installations:

```sh
java -version    # should display 11.x
mvn -version
gradle -version  # 6.9.2
docker --version
docker compose version
```

## Step 1: Prepare CodeNarc

The Android plugin uses [CodeNarc](https://codenarc.org/) to analyze Gradle files. It must be built separately and installed in the local Maven repository.

```sh
# From the project root
./tool_prepare-codenarc.sh
```

This script does two things:
1. Builds CodeNarc (in `codenarc-converter/CodeNarc/`) via Gradle
2. Installs the resulting JAR into the local Maven repository (`~/.m2/repository`)

> **Common issue**: if Gradle uses the wrong Java version, force it with `export JAVA_HOME=$(/usr/libexec/java_home -v 11)` before running the script. Ideally, use [sdkman](https://sdkman.io/) to manage Java versions.

## Step 2: Build the Plugin

```sh
# From the project root
./tool_build.sh
```

Or manually:

```sh
mvn clean package -DskipTests
```

The plugin JAR is generated at:
```
android-plugin/target/creedengo-android-2.0.0-SNAPSHOT.jar
```

It is also automatically copied to `./lib/` by the Maven build.

### Run tests (optional)

```sh
mvn clean verify
```

## Step 3: Start SonarQube with Docker Compose

The `android-plugin/docker-compose.yml` file is preconfigured to mount the plugin JAR directly into SonarQube.

> **Important**: The Docker image version must be compatible with the `sonarqube.version` declared in the root `pom.xml`. If SonarQube rejects the plugin at startup, check the logs (`docker compose logs sonar`) for an API version mismatch.

```sh
cd android-plugin
docker compose up -d
```

This starts:
- **SonarQube Community Edition** on port `9000`
- **PostgreSQL 12** as the database

The plugin is mounted via a bind mount:
```
./target/creedengo-android-2.0.0-SNAPSHOT.jar → /opt/sonarqube/extensions/plugins/
```

### Check startup

```sh
docker compose logs -f sonar
```

Wait for the `SonarQube is operational` message (may take 1-2 minutes).

## Step 4: Access SonarQube

1. Open http://localhost:9000
2. Log in with default credentials: **admin / admin**
3. SonarQube will ask to change the password on first login

### Verify the plugin is installed

Go to **Administration → Marketplace → Installed** (or **Administration → Plugins**).

The "Creedengo Android Java plugin" should appear in the list.

You can also check available rules:
- **Rules → Repository**: search for "creedengo" to see the plugin's rules

## Step 5: Analyze an Android Project

To run an analysis on a local Android project:

```sh
cd /path/to/your/android-project

mvn sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=admin \
  -Dsonar.password=your_new_password
```

Or with a token (recommended):

```sh
# Generate a token in SonarQube: My Account → Security → Generate Tokens

mvn sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=squ_xxxxxxxxxxxxx
```

## Development Cycle

When you modify the plugin and want to test changes:

```sh
# 1. Rebuild the plugin
cd /path/to/creedengo-android
mvn clean package -DskipTests

# 2. Restart SonarQube to load the new JAR
cd android-plugin
docker compose restart sonar

# 3. Wait for SonarQube to be operational, then re-run the analysis
```

## Stop / Clean Up

```sh
cd android-plugin

# Stop containers (data is preserved in volumes)
docker compose down

# Stop AND remove volumes (full reset)
docker compose down -v
```

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `Permission denied` on .sh scripts | `chmod +x tool_*.sh` |
| SonarQube won't start (Elasticsearch) | Increase `vm.max_map_count`: `sudo sysctl -w vm.max_map_count=262144` |
| Plugin not visible after restart | Check that the JAR exists in `android-plugin/target/` and that the name matches the bind mount in `docker-compose.yml` |
| Plugin rejected (API version mismatch) | Update the Docker image version to match `sonarqube.version` in `pom.xml` |
| CodeNarc compilation error | Verify that `JAVA_HOME` points to Java 11 |
| Port 9000 already in use | Find what's using it: `lsof -i :9000`, then either stop it or change the port in `docker-compose.yml` to e.g. `"9001:9000"` |
| `OutOfMemoryError` during Maven build | `export MAVEN_OPTS="-Xmx1024m"` |
