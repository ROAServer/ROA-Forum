package cn.mercury9.utils.skiko

import org.jetbrains.skiko.OS

/**
 * Ios or MacOS
 */
inline fun OS.isApple(): Boolean {
  return this == OS.Ios || this == OS.MacOS
}
