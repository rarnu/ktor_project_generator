package com.rarnu.ktor.generator

import java.io.File

private fun applicationConf(outFile: File, pkgName: String) {
    val arr = mutableListOf<String>()
    with(arr) {
        add("ktor {")
        add("   application {")
        add("       modules = [ $pkgName.ApplicationKt.main ]")
        add("   }")
        add("}")
    }
    File(outFile, "resources").mkdirs()
    File(outFile, "resources/application.conf").writeText(arr.joinToString("\n"))
}

private fun webXml(outFile: File)  {
    val arr = mutableListOf<String>()
    with(arr) {
        add("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        add("<web-app xmlns=\"http://java.sun.com/xml/ns/javaee\"")
        add("       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
        add("       xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd\"")
        add("       version=\"3.0\">")
        add("   <servlet>")
        add("       <display-name>KtorServlet</display-name>")
        add("       <servlet-name>KtorServlet</servlet-name>")
        add("       <servlet-class>io.ktor.server.servlet.ServletApplicationEngine</servlet-class>")
        add("       <init-param>")
        add("           <param-name>io.ktor.config</param-name>")
        add("           <param-value>application.conf</param-value>")
        add("       </init-param>")
        add("       <async-supported>true</async-supported>")
        add("       <!-- 100mb max file upload, optional -->")
        add("       <multipart-config>")
        add("           <max-file-size>304857600</max-file-size>")
        add("           <max-request-size>304857600</max-request-size>")
        add("           <file-size-threshold>0</file-size-threshold>")
        add("       </multipart-config>")
        add("   </servlet>")
        add("   <servlet-mapping>")
        add("       <servlet-name>KtorServlet</servlet-name>")
        add("       <url-pattern>/</url-pattern>")
        add("   </servlet-mapping>")
        add("</web-app>")
    }
    File(outFile, "web/WEB-INF").mkdirs()
    File(outFile, "web/WEB-INF/web.xml").writeText(arr.joinToString("\n"))
}

private fun applicationKt(outFile: File, pkgName: String) {
    val arr = mutableListOf<String>()
    with(arr) {
        add("package $pkgName")
        add("import io.ktor.application.Application")
        add("import io.ktor.application.call")
        add("import io.ktor.html.respondHtml")
        add("import io.ktor.response.respond")
        add("import io.ktor.routing.get")
        add("import io.ktor.routing.routing")
        add("import kotlinx.html.body")
        add("import kotlinx.html.head")
        add("import kotlinx.html.p")
        add("import kotlinx.html.title")
        add("")
        add("fun Application.main() {")
        add("   routing {")
        add("       get(\"/\") {")
        add("           call.respondHtml {")
        add("               head {")
        add("                   title { + \"Ktor\" }")
        add("               }")
        add("               body {")
        add("                   p { + \"Hello from Ktor\" }")
        add("               }")
        add("           }")
        add("       }")
        add("       get(\"/api\") {")
        add("           call.respondText { \"{\\\"result\\\":0}\" }")
        add("       }")
        add("   }")
        add("}")
    }
    File(outFile, "src").mkdirs()
    File(outFile, "src/Application.kt").writeText(arr.joinToString("\n"))
}

fun makeSrcFiles(outFile: File, pkgName: String) {
    applicationConf(outFile, pkgName)
    webXml(outFile)
    applicationKt(outFile, pkgName)
}