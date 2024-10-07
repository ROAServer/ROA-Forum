plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "cn.mercury9.roa.forum"
version = "1.0.0"
application {
    mainClass.set("cn.mercury9.roa.forum.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}