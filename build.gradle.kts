import org.jetbrains.kotlin.org.apache.commons.io.output.ByteArrayOutputStream

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

tasks {
  val countKtSourceFileLinesAndWriteToREADME by creating {
    doLast {
      val out = ByteArrayOutputStream()
      val commandCountKt = "git ls-files '*.kt' | xargs cat | wc -l"

      exec {
        standardOutput = out
        workingDir = projectDir
        commandLine("sh", "-c", commandCountKt)
      }

      val ktLines = out.toString(Charsets.UTF_8).filterNot {
        it.isWhitespace()
      }
      println("$ktLines lines kt")

      val readmeFile = project.file("README.md")

      readmeFile.readText().apply {
        replace(
          this.lines().filter {
            it.startsWith("[![Kotlin](https://img.shields.io/badge/")
          }[0],
          "[![Kotlin](https://img.shields.io/badge/${ktLines}_lines-Kotlin-7954F6?logo=kotlin)](https://kotlinlang.org/)"
        ).also {
          readmeFile.writeText(it)
        }
      }

    }
  }
}
