package cn.mercury9.roa.forum.utils.system

enum class OS(val id: String) {
    Android("android"),
    Linux("linux"),
    Windows("windows"),
    MacOS("macos"),
    Ios("ios"),
    Tvos("tvos"),

    Unknown("unknown")
    ;

    val isLinux
        get() = this == Linux

    val isWindows
        get() = this == Windows

    val isMacOS
        get() = this == MacOS

    val isApple
        get() = this == MacOS || this == Ios
}

enum class KotlinBackend(val id: String) {
    JVM("jvm"),
    JS("js"),
    Native("native"),
    Wasm("wasm"),
    ;

    fun isNotJs() = this != JS
    fun isWeb() = this == JS || this == Wasm || this.id == "wasm"
}

// Note: it returns the host OS for web apps too
expect val hostOs: OS

expect val kotlinBackend: KotlinBackend

