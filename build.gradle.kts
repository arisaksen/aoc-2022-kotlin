plugins {
    kotlin("jvm") version "1.7.22"
}

dependencies {
    api("org.assertj:assertj-core:3.23.1")
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
