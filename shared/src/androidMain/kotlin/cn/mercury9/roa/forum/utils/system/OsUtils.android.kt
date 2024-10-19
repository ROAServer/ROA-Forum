package cn.mercury9.roa.forum.utils.system

actual val hostOs: OS
    get() = OS.Android

actual val kotlinBackend: KotlinBackend
    get() = KotlinBackend.JVM
