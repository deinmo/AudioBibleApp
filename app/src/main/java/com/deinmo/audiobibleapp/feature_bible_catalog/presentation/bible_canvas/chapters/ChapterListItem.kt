package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Chapters

@Composable
fun ChapterListItem(
    chapters: Chapters,
    onitemclick: (Chapters) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onitemclick(chapters) }
            .padding(16.dp)
    ) {
        Text(text = "Chapter " + chapters.number,style = MaterialTheme.typography.body1)
    }
}