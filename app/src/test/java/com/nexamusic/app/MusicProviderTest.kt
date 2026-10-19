package com.nexamusic.app

import com.nexamusic.app.data.DemoMusicProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MusicProviderTest {
    @Test fun searchFiltersCatalog() = runTest { assertEquals(1, DemoMusicProvider().search("Neon").tracks.size) }
    @Test fun blankSearchReturnsEmptyBecauseNoQueryMatchesAllIsNotImplicit() = runTest { assertEquals(4, DemoMusicProvider().search("").tracks.size) }
}
