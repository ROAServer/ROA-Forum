package cn.mercury9.roa.forum

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
  override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
