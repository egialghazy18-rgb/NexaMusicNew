# NexaMusic

NexaMusic is a native Android music-player foundation written in Kotlin with Jetpack Compose, Material 3, MVVM-style state, and AndroidX Media3. It includes a real foreground playback service, system media controls, search over a replaceable `MusicProvider`, a dark neon UI, and a GitHub Actions debug APK build.

## Build locally

Use JDK 17 and Android SDK platform 35. Then run:

```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

The APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

## GitHub Actions

Push to `main`, or run **Android Build** manually from the Actions tab. The workflow runs unit tests, builds `assembleDebug`, and uploads `NexaMusic-debug.apk` as an artifact.

## Provider and playback scope

The app deliberately does not scrape YouTube Music, generate undocumented audio URLs, bypass DRM, or store Google credentials. The default `DemoMusicProvider` supplies demo streams so the Media3 engine and background playback can be tested end-to-end. YouTube's official API does not provide a general raw-audio streaming endpoint for this use case, and YouTube Terms restrict unauthorized access, copying, transmission, and circumvention. See [RESEARCH.md](RESEARCH.md).

The `MusicProvider` interface is the extension point for a provider with an authorized playback contract. If a source cannot provide lawful audio access, the correct fallback is metadata plus handoff to the source's official app/player rather than a fake success state.

## Current implementation

- Native Kotlin Android app, min API 26, target API 35.
- Jetpack Compose + Material 3 dark UI.
- Media3 ExoPlayer in `PlaybackService` with `MediaSessionService`, audio focus, notification and lock-screen/system controls.
- Search and quick-pick catalog through `MusicProvider`.
- GitHub Actions with pinned action versions and artifact upload.
- Unit tests for provider search.

## License

The project code in this repository is released under the MIT License. Demo stream and image URLs remain subject to their respective providers' terms.
