import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.kotlinx.serialization)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_11)
    }
  }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  jvm()

  sourceSets {
    commonMain.dependencies {
//      implementation(libs.logback)

      implementation(libs.kotlinx.coroutines.core)
      implementation(libs.kotlinx.serialization.json)
      implementation(libs.kotlinx.timedate)

      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.auth)

      implementation(libs.crypto.core.digest)
      implementation(libs.crypto.hash.sha2)

      implementation(libs.multiplatform.settings)
    }
    androidMain.dependencies {
      implementation(libs.ktor.client.okhttp)
      implementation(libs.androidx.startup.runtime)
    }
    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
    }
    jvmMain.dependencies {
      implementation(libs.ktor.client.cio)
    }
  }
}

android {
  namespace = "cn.mercury9.roa.forum.shared"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }
}
