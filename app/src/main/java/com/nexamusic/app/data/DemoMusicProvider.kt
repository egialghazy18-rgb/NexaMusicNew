package com.nexamusic.app.data

class DemoMusicProvider : MusicProvider {
    private val catalog = listOf(
        Track("nexa-01", "A Neon Dawn", "Nexa Sessions", "Midnight Circuit", "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=800", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"),
        Track("nexa-02", "Electric Horizon", "Nexa Sessions", "Midnight Circuit", "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"),
        Track("nexa-03", "Afterglow", "Nexa Sessions", "City Lights", "https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3?w=800", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"),
        Track("nexa-04", "Night Drive", "Nexa Sessions", "City Lights", "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=800", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3")
    )
    override suspend fun search(query: String) = SearchResults(catalog.filter { "${it.title} ${it.artist} ${it.album}".contains(query, true) }, query)
    override suspend fun quickPicks() = catalog
}

class MusicRepository(private val provider: MusicProvider) {
    suspend fun search(query: String) = provider.search(query)
    suspend fun quickPicks() = provider.quickPicks()
}
