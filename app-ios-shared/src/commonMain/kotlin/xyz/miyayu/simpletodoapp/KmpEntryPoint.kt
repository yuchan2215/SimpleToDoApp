package xyz.miyayu.simpletodoapp

import xyz.miyayu.simpletodoapp.core.data.Platform
import xyz.miyayu.simpletodoapp.core.data.getPlatform

class KmpEntryPoint {
    fun providePlatform(): Platform = getPlatform()
}
