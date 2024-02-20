package xyz.miyayu.simpletodoapp.core.data

class AndroidPlatform : Platform {
    override val name: String
        get() = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
