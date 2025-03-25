package com.gowthamraj07.tasklite

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform