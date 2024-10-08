package cn.mercury9.roa.forum.data.storage

import kotlin.properties.ReadWriteProperty

interface DataStorage<T> {
  fun get(): T
  fun set(value: T)
  fun value(): ReadWriteProperty<Any?, T>
}
