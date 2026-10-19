package com.nexamusic.app

import android.app.Application
import com.nexamusic.app.data.DemoMusicProvider
import com.nexamusic.app.data.MusicRepository

class NexaMusicApplication : Application() {
    val repository by lazy { MusicRepository(DemoMusicProvider()) }
}
