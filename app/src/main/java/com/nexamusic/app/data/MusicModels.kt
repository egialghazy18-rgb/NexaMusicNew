package com.nexamusic.app.data

data class Track(val id: String, val title: String, val artist: String, val album: String, val artworkUrl: String?, val streamUrl: String, val durationMs: Long = 0L)

data class SearchResults(val tracks: List<Track> = emptyList(), val query: String = "")

interface MusicProvider { suspend fun search(query: String): SearchResults; suspend fun quickPicks(): List<Track> }
