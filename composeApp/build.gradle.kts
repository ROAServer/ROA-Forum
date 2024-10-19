import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Info {
  const val GROUP = "cn.mercury9.roa.forum"
  const val VERSION_NAME = "1.0.0"
  const val VERSION_CODE = 1
  val JVM_TARGET = JvmTarget.JVM_11
  val JAVA_VERSION = JavaVersion.VERSION_11
}

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlinx.serialization)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(Info.JVM_TARGET)
    }
  }

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    val desktopMain by getting

    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)

      implementation(libs.androidx.startup.runtime)

      implementation(libs.androidx.compose.materialWindow)
      implementation(libs.androidx.window)
      implementation(libs.androidx.window.core)

      implementation(libs.kotlinx.coroutines.android)
    }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)

      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.navigation)

      implementation(libs.compose.lifecycle.viewmodle)

      implementation(libs.kotlinx.coroutines.core)
      implementation(libs.kotlinx.serialization.json)

      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.auth)

      implementation(libs.multiplatform.settings)
      implementation(libs.multiplatform.settings.serialization)

      implementation(libs.haze)
      implementation(libs.haze.materials)

      implementation(projects.shared)
    }
    desktopMain.dependencies {
      implementation(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
      }
      implementation(libs.kotlinx.coroutines.swing)

      // https://github.com/JetBrains/Jewel
      implementation("org.jetbrains.jewel:jewel-int-ui-standalone-242:0.25.0")
      // For custom decorated windows:
      implementation("org.jetbrains.jewel:jewel-int-ui-decorated-window-242:0.25.0")
    }
  }
}

android {
  namespace = Info.GROUP
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = Info.GROUP
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = Info.VERSION_CODE
    versionName = Info.VERSION_NAME
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = Info.JAVA_VERSION
    targetCompatibility = Info.JAVA_VERSION
  }
  buildFeatures {
    compose = true
  }
  dependencies {
    debugImplementation(compose.uiTooling)
  }
}

compose.desktop {
  application {
    mainClass = "${Info.GROUP}.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = Info.GROUP
      packageVersion = Info.VERSION_NAME
    }
  }
}

compose.resources {
  publicResClass = true
  packageOfResClass = "${Info.GROUP}.resources"
  generateResClass = always
}

tasks {
  val replaceAndroidColorInVectorDrawables by creating {
    doLast {
      val drawableFileTree = fileTree("src/commonMain/composeResources/drawable") {
        include("*.xml")
      }
      val filesProcessed: MutableList<String> = mutableListOf()
      drawableFileTree.forEach {
        if (it.readLines()[0].startsWith("<vector")) {
          println("Processing: ${it.name}")
          val oldContent = it.readText()
          val newContent = oldContent.replace(
            "@android:color/white",
            "#ffffff"
          )
          if (newContent != oldContent) {
            it.writeText(newContent)
            println(" - Processed.")
            filesProcessed += it.name
          } else {
            println(" - Pass.")
          }
        }
      }
      println("Process finished, ${filesProcessed.size} file(s) processed.")
      println(filesProcessed)
    }
  }.apply {
    description =
      "Replace \"@android:color/white\" " +
          "in vector drawables that download from Google Icons " +
          "to \"#ffffff\""
  }
}
