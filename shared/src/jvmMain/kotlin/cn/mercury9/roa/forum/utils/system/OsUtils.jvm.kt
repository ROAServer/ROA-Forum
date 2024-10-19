package cn.mercury9.roa.forum.utils.system

actual val hostOs: OS by lazy {
    val osName = System.getProperty("os.name")
    when {
        osName == "Mac OS X" -> OS.MacOS
        osName.startsWith("Win") -> OS.Windows
        "The Android Project" == System.getProperty("java.specification.vendor") -> OS.Android
        osName == "Linux" -> OS.Linux
        else -> OS.Unknown
    }
}

actual val kotlinBackend: KotlinBackend
    get() = KotlinBackend.JVM