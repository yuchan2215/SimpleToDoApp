package xyz.miyayu.simpletodoapp.core.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
