package com.gowthamraj07.tasklite.utils

// androidMain
actual object RandomUUIDFactory {
    actual fun create(): String = java.util.UUID.randomUUID().toString()
}
