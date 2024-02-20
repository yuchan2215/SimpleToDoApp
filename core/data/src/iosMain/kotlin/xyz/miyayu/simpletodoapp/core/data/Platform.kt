package xyz.miyayu.simpletodoapp.core.data

class IosPlatform : Platform {
    override val name: String
        get() = "ios"
}

actual fun getPlatform(): Platform = IosPlatform()
