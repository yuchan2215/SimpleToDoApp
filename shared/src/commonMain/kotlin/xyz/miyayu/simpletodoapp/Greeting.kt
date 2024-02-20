package xyz.miyayu.simpletodoapp

class Greeting {
    private val platform: Platform = getPlatform()

    /**
     * 挨拶を返す
     */
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
