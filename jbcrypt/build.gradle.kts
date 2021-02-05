buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    `java-library`
    id("com.jfrog.artifactory") version "4.19.0"
    id("com.jfrog.bintray") version "1.8.5"
    id("net.researchgate.release") version "2.8.1"
}

apply(from = "${rootDir}/publish.gradle")

dependencies {
    testCompile("junit:junit:4.13.2")
}
