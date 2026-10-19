package com.nexamusic.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nexamusic.app.data.Track

@Composable fun NexaMusicApp(state: PlayerState, vm: NexaMusicViewModel) {
    val context = LocalContext.current; var query by remember { mutableStateOf("") }; var tab by remember { mutableIntStateOf(0) }
    Scaffold(containerColor = Color(0xFF080A12), bottomBar = { NavigationBar(containerColor = Color(0xFF101321)) { listOf(Icons.Default.Home to "Home", Icons.Default.Search to "Search", Icons.Default.Favorite to "Library", Icons.Default.Settings to "Settings").forEachIndexed { i, p -> NavigationBarItem(selected = tab == i, onClick = { tab = i }, icon = { Icon(p.first, p.second) }, label = { Text(p.second) }) } } }) { pad ->
        LazyColumn(Modifier.fillMaxSize().padding(pad).padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item { Spacer(Modifier.height(14.dp)); Text("Good evening", color = Color(0xFF9CA3AF), fontSize = 14.sp); Text("NexaMusic", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold); Spacer(Modifier.height(8.dp)); OutlinedTextField(value = query, onValueChange = { query = it; vm.search(context, it) }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Search songs, artists, albums", color = Color(0xFF8991A6)) }, leadingIcon = { Icon(Icons.Default.Search, null) }, singleLine = true, shape = RoundedCornerShape(18.dp), colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color(0xFF293047), focusedBorderColor = Color(0xFFA78BFA), unfocusedContainerColor = Color(0xFF121624), focusedContainerColor = Color(0xFF121624), unfocusedTextColor = Color.White, focusedTextColor = Color.White)) }
            item { Text(if (query.isBlank()) "Quick picks" else "Search results", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold) }
            items(state.tracks, key = { it.id }) { track -> TrackRow(track) { vm.play(track) } }
            if (state.tracks.isEmpty() && !state.isLoading) item { EmptyState(query) }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable private fun TrackRow(track: Track, onClick: () -> Unit) { Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0xFF121624)).clickable(onClick = onClick).padding(12.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(track.artworkUrl, null, Modifier.size(62.dp).clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop); Column(Modifier.weight(1f).padding(horizontal = 14.dp)) { Text(track.title, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp); Text("${track.artist} · ${track.album}", color = Color(0xFF9CA3AF), fontSize = 13.sp) }; Icon(Icons.Default.PlayArrow, "Play", tint = Color(0xFFA78BFA)) } }
@Composable private fun EmptyState(query: String) { Box(Modifier.fillMaxWidth().height(180.dp), contentAlignment = Alignment.Center) { Text(if (query.isBlank()) "Your music library is ready" else "No matching tracks", color = Color(0xFF9CA3AF)) } }
