package xyz.miyayu.simpletodoapp

/**
 * にゃーんなクラス
 */
interface Platform {
    val name: String
}

// Implementation This Error
expect fun getPlatform(): Platform
