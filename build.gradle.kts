plugins {
    application
    id("idea")
    id("org.jetbrains.kotlin.jvm")
}

application {
    mainClassName = "com.github.vacxe.apkinfoextractor.ApkPublisherKt"
    applicationName = "apkinfoextractor"
}

repositories {
    jcenter()
}

dependencies {
    implementation(Libraries.argParser)
    implementation(Libraries.axmlParser)
    implementation(Libraries.gson)
}
