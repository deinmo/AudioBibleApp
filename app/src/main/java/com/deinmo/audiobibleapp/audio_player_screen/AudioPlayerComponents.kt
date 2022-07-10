package com.deinmo.audiobibleapp.audio_player_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class AudioControlstate(
    val isplaying:Boolean = false,
    val isplaybuttonenabled: Boolean = true,
    val isnextbuttonenabled: Boolean = true,
    val ispreviousbuttonenabled: Boolean = true,
    val onPlaylistbuttonClicked: () -> Unit = {},
    val onplaybuttonClicked: () -> Unit = {},
    val onNextbuttonClicked: () -> Unit = {},
    val onPreviousplaybuttonClicked: () -> Unit = {},
)

data class AudioSliderState(
    val sliderValue: Float = 0f,
    val timepassed: Long = 0L,
    val timeleft: Long = 0L,
    val onValueChange: (Float) -> Unit = {}
){
    val timepassedformatted
    get() = timepassed.formatduration()

    val timeleftformatted
    get() = timeleft.formatduration()
}

fun Long.formatduration(): String{
    var seconds = this/1000
    var minutes = seconds/ 60
    seconds %= 60
    return String.format("%02d:%02d",minutes,seconds)
}
@Composable
fun RoundImageButton(
    image: ImageVector,
    icontint: Color,
    backgroungcolor: Color,
    contentDescription: String,
    iconOffset: Dp = 0.dp,
    isEnabled: Boolean = true,
    onclick: () -> Unit
){
    IconButton(
        onClick = onclick,
        modifier = Modifier
            .background(color = backgroungcolor)
        //    .aspectRatio(1f)
            .clip(CircleShape),
        enabled = isEnabled
    ) {
        Icon(imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier
         //       .fillMaxSize()
                .aspectRatio(1f))
    }
}

@Composable
fun playpausebutton(
    isplaying: Boolean,
    isEnabled: Boolean = false,
    onclick: () -> Unit
){
    RoundImageButton(
        image = if (isplaying) Icons.Default.PlayArrow else Icons.Default.Done,
        icontint = MaterialTheme.colorScheme.primary,
        backgroungcolor = MaterialTheme.colorScheme.primaryContainer,
        contentDescription = "Play/Pause",
        onclick = onclick,
        isEnabled = isEnabled,
        iconOffset = if (isplaying) 0.dp else 4.dp
    )
}

@Composable
fun previoustrackbutton(
    isEnabled: Boolean = false,
    onclick: () -> Unit
){
    RoundImageButton(
        image = Icons.Default.KeyboardArrowLeft,
        icontint = MaterialTheme.colorScheme.primary,
        backgroungcolor = MaterialTheme.colorScheme.primaryContainer,
        contentDescription = "Play/Pause",
        onclick = onclick,
        isEnabled = isEnabled
    )
}

@Composable
fun nextbutton(
    isEnabled: Boolean = false,
    onclick: () -> Unit
){
    RoundImageButton(
        image = Icons.Default.KeyboardArrowRight,
        icontint = MaterialTheme.colorScheme.primary,
        backgroungcolor = MaterialTheme.colorScheme.primaryContainer,
        contentDescription = "Play/Pause",
        onclick = onclick,
        isEnabled = isEnabled
    )
}

@Composable
fun playlistbutton(
    isEnabled: Boolean = false,
    onclick: () -> Unit
){
    RoundImageButton(
        image = Icons.Default.List,
        icontint = MaterialTheme.colorScheme.primary,
        backgroungcolor = MaterialTheme.colorScheme.primaryContainer,
        contentDescription = "Play/Pause",
        onclick = onclick,
        isEnabled = isEnabled
    )
}

@Composable
fun Loopbutton(
    isEnabled: Boolean = false,
    onclick: () -> Unit
){
    RoundImageButton(
        image = Icons.Default.Send,
        icontint = MaterialTheme.colorScheme.primary,
        backgroungcolor = MaterialTheme.colorScheme.primaryContainer,
        contentDescription = "Old Track",
        onclick = onclick,
        isEnabled = isEnabled
    )
}

@Composable
fun MusicSlider(
    slidercolor: Color,
    audioSliderState: AudioSliderState
){
    Slider(
        value = audioSliderState.sliderValue,
        onValueChange = audioSliderState.onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    colors = SliderDefaults.colors(
        activeTrackColor = slidercolor.copy(alpha = 0.7f),
        inactiveTickColor = slidercolor.copy(alpha = 0.4f),
        thumbColor = slidercolor
    ))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ){
        Text(
            text = audioSliderState.timepassedformatted,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.tertiary)

        Text(
            text = audioSliderState.timeleftformatted,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
fun TitleArea(
    songName: String
){
    Text(
        text = songName,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold),
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 16.dp)
    )
}