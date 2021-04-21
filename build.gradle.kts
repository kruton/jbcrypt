// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

repositories {
    mavenCentral()
}

plugins {
    `java-library`
    `maven-publish`
    signing
    id("net.researchgate.release") version "2.8.1"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "org.connectbot"

val gitHubUrl = "https://github.com/kruton/jbcrypt"

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    testCompile("junit:junit:4.13.2")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("jBCrypt")
                description.set("A fork of jBCrypt with more modern OpenBSD algorithms.")
                url.set(gitHubUrl)
                licenses {
                    license {
                        name.set("ISC")
                        url.set("https://www.isc.org/downloads/software-support-policy/isc-license/")
                    }
                }
                developers {
                    developer {
                        name.set("Kenny Root")
                        email.set("kenny@the-b.org")
                    }
                }
                scm {
                    connection.set("${gitHubUrl}.git")
                    developerConnection.set("${gitHubUrl}.git")
                    url.set(gitHubUrl)
                }
            }
        }
    }
}

signing {
    setRequired({
        gradle.taskGraph.hasTask("publish")
    })
    sign(publishing.publications["mavenJava"])
}

nexusPublishing {
    repositories {
        sonatype()
    }
}
