package cn.mercury9.roa.forum.utils.system

actual val hostOs: OS
  get() = OS.Ios

actual val kotlinBackend: KotlinBackend
  get() = KotlinBackend.Native
