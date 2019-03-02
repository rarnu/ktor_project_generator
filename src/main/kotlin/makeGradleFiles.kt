package com.rarnu.ktor.generator

import java.io.File

private fun buildGradle(outFile: File) {
    val arr = mutableListOf<String>()
    with(arr) {
        add("buildscript {")
        add("   ext.ktor_version = \"1.1.2\"")
        add("   ext.kotlin_version = '1.3.20'")
        add("   repositories {")
        add("       google()")
        add("       jcenter()")
        add("       maven { url 'https://dl.bintray.com/kotlin/ktor' }")
        add("       maven { url 'https://dl.bintray.com/kotlin/kotlin-eap' }")
        add("   }")
        add("   dependencies {")
        add("       classpath \"org.jetbrains.kotlin:kotlin-gradle-plugin:\$kotlin_version\"")
        add("       classpath \"com.android.tools.build:gradle:3.1.4\"")
        add("   }")
        add("}")
        add("apply plugin: \"kotlin\"")
        add("apply plugin: \"war\"")
        add("webAppDirName = \"web\"")
        add("sourceSets {")
        add("   main.kotlin.srcDirs = [ \"src\" ]")
        add("   main.resources.srcDirs = [ \"resources\" ]")
        add("}")
        add("repositories {")
        add("   google()")
        add("   jcenter()")
        add("   mavenCentral()")
        add("   maven { url 'https://dl.bintray.com/kotlin/kotlin-eap' }")
        add("}")
        add("dependencies {")
        add("   compile \"org.jetbrains.kotlin:kotlin-stdlib-jdk8:\$kotlin_version\"")
        add("   compile \"io.ktor:ktor-server-servlet:\$ktor_version\"")
        add("   compile \"io.ktor:ktor-html-builder:\$ktor_version\"")
        add("}")
    }
    File(outFile, "build.gradle").writeText(arr.joinToString("\n"))
}

private fun gradleProperties(outFile: File) {
    File(outFile, "gradle.properties").writeText("kotlin.code.style=official")
}

private fun settingsGradle(outFile: File, projName: String)  {
    File(outFile, "settings.gradle").writeText("rootProject.name = \"$projName\"")
}

fun makeGradleFiles(outFile: File, projName: String) {
    buildGradle(outFile)
    gradleProperties(outFile)
    settingsGradle(outFile, projName)
}