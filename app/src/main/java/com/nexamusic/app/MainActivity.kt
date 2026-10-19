package com.nexamusic.app

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nexamusic.app.ui.NexaMusicApp
import com.nexamusic.app.ui.NexaMusicViewModel
import com.nexamusic.app.ui.theme.NexaMusicTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<NexaMusicViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); viewModel.connect(this, ComponentName(this, com.nexamusic.app.playback.PlaybackService::class.java)); setContent { NexaMusicTheme { val state by viewModel.state.collectAsStateWithLifecycle(); NexaMusicApp(state, viewModel) } } }
}
