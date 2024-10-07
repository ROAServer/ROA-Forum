package cn.mercury9.roa.forum.data.storage

interface DataStorage<T> {
  fun get(): T
  fun set(value: T)
  var onValueSet: (value: T) -> Unit
}
