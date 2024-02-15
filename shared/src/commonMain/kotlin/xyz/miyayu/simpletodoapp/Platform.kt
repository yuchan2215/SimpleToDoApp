package xyz.miyayu.simpletodoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform