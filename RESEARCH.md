# NexaMusic provider audit

NexaMusic uses `MusicProvider` as a replaceable boundary. The initial build ships with `DemoMusicProvider`, which uses openly reachable demo streams so Media3 background playback can be exercised without pretending that YouTube Data API supplies raw audio URLs.

The official Android Media3 guidance recommends encapsulating the player and `MediaSession` inside a foreground `MediaSessionService`; the service publishes a media notification and exposes the session to UI controllers. See [Android playback app documentation](https://developer.android.com/media/implement/playback-app).

Metrolist demonstrates that third-party YouTube Music clients exist, but its own repository currently describes itself as maintenance mode and is GPL-3.0. More importantly, YouTube's Terms restrict accessing, reproducing, downloading, transmitting, or circumventing service protections except where expressly authorized. This repo therefore does not copy Metrolist code, scrape Innertube audio URLs, bypass DRM, or ask users for Google passwords. See [Metrolist](https://github.com/metrolistgroup/metrolist), [YouTube Terms](https://www.youtube.com/static?template=terms), and [YouTube API Services Terms](https://developers.google.com/youtube/terms/api-services-terms-of-service).

A future provider can implement authorized catalog metadata or hand off playback to an official YouTube surface, but it must preserve these constraints. The app's README states this limitation explicitly.
