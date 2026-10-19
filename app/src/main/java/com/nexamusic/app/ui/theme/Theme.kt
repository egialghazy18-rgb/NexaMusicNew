package com.nexamusic.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(primary = Color(0xFFA78BFA), secondary = Color(0xFF60A5FA), background = Color(0xFF080A12), surface = Color(0xFF121624))
@Composable fun NexaMusicTheme(content: @Composable () -> Unit) { MaterialTheme(colorScheme = DarkColors, typography = Typography(), content = content) }
