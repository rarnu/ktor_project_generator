package com.rarnu.ktor.generator

import java.io.File

private fun writeHelp() {
    println("usage:")
    println("  java -jar ktorgenerator-1.0.jar <project name> <package name>")
    println("")
    println("sample:")
    println("  ktorgenerator hello com.rarnu.hello")
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        writeHelp()
        return
    }
    val projName = args[0]
    val pkgName = args[1]
    val basePath = System.getProperty("user.dir")

    val outFile = File(basePath, projName)
    if (outFile.exists()) {
        println("Project exists.")
        return
    }
    outFile.mkdirs()

    makeGradleFiles(outFile, projName)
    makeSrcFiles(outFile, pkgName)
}
