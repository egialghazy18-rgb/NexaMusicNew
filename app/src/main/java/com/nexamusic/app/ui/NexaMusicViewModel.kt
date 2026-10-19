package com.nexamusic.app.ui

import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.nexamusic.app.NexaMusicApplication
import com.nexamusic.app.data.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PlayerState(val tracks: List<Track> = emptyList(), val query: String = "", val selected: Track? = null, val isLoading: Boolean = false, val error: String? = null)

class NexaMusicViewModel : ViewModel() {
    private val _state = MutableStateFlow(PlayerState()); val state: StateFlow<PlayerState> = _state.asStateFlow(); private var controller: MediaController? = null
    fun connect(context: Context, component: ComponentName) { val future = MediaController.Builder(context, SessionToken(context, component)).buildAsync(); future.addListener({ controller = future.get() }, MoreExecutors.directExecutor()); loadPicks(context) }
    private fun loadPicks(context: Context) { viewModelScope.launch { runCatching { (context.applicationContext as NexaMusicApplication).repository.quickPicks() }.onSuccess { _state.value = _state.value.copy(tracks = it) }.onFailure { _state.value = _state.value.copy(error = it.message) } } }
    fun search(context: Context, query: String) { _state.value = _state.value.copy(query = query, isLoading = true); viewModelScope.launch { runCatching { (context.applicationContext as NexaMusicApplication).repository.search(query) }.onSuccess { _state.value = _state.value.copy(tracks = it.tracks, isLoading = false) }.onFailure { _state.value = _state.value.copy(isLoading = false, error = it.message) } } }
    fun play(track: Track) { val item = MediaItem.Builder().setMediaId(track.id).setUri(track.streamUrl).setMediaMetadata(MediaMetadata.Builder().setTitle(track.title).setArtist(track.artist).setAlbumTitle(track.album).setArtworkUri(track.artworkUrl?.let(android.net.Uri::parse)).build()).build(); controller?.setMediaItem(item); controller?.prepare(); controller?.play(); _state.value = _state.value.copy(selected = track) }
    override fun onCleared() { controller?.release(); super.onCleared() }
}
