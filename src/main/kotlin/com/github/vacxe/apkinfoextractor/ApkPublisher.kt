package com.github.vacxe.apkinfoextractor

import com.google.gson.Gson
import com.shazam.axmlparser.AXMLParser
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipFile

fun main(args: Array<String>) {

    if (args.isEmpty()) {

        throw RuntimeException("Empty arguments")
    }
    val apk = File(args[0])

    if (!apk.exists()) {
        throw RuntimeException("File ${apk.absoluteFile} doesn't exist")
    }

    var apkInputStream: InputStream? = null
    try {
        val zip = ZipFile(apk)
        val entry = zip.getEntry("AndroidManifest.xml")
        apkInputStream = zip.getInputStream(entry)

        val parser = AXMLParser(apkInputStream)
        var eventType = parser.type

        var appPackage: String? = null
        var versionCode: Int? = null
        var versionName: String? = null
        while (eventType != AXMLParser.END_DOCUMENT) {
            if (eventType == AXMLParser.START_TAG) {
                val parserName = parser.name
                val isManifest = "manifest" == parserName
                if (isManifest) {
                    for (i in 0 until parser.attributeCount) {
                        when (parser.getAttributeName(i)) {
                            "package" -> {
                                appPackage = parser.getAttributeValueString(i)
                            }
                            "versionCode" -> {
                                versionCode = parser.getAttributeValue(i)
                            }
                            "versionName" -> {
                                versionName = parser.getAttributeValueString(i)
                            }
                        }
                    }
                }
            }
            eventType = parser.next()
        }
        println(Gson().toJson(ApkInfo(appPackage, versionCode, versionName)))
    } catch (e: IOException) {
        throw RuntimeException("Unable to parse AndroidManifest.xml.", e)
    } finally {
        apkInputStream?.close()
    }
}
