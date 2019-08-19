package com.atybaty.timer.util

import android.content.Context
import android.media.MediaPlayer


class AudioPlayer(private val context: Context) {
    private lateinit var nestedPlayer: MediaPlayer

    fun playSound(soundName: String) {
        if (::nestedPlayer.isInitialized) {
            nestedPlayer.stop()
            nestedPlayer.release()
        }
        nestedPlayer = MediaPlayer()

        val descriptor = context.assets.openFd("sounds/$soundName.mp3")
        nestedPlayer.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
        descriptor.close()

        nestedPlayer.prepare()
        nestedPlayer.setVolume(1f, 1f)
        nestedPlayer.isLooping = false
        nestedPlayer.start()
    }
}
