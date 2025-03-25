package com.gowthamraj07.tasklite.utils

// iosMain
import platform.Foundation.NSUUID

actual object RandomUUIDFactory {
    actual fun create(): String = NSUUID().UUIDString()
}
