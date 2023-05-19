package com.erkindilekci.textbook.feature_note.presentation.notes.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erkindilekci.textbook.R
import com.erkindilekci.textbook.feature_note.presentation.notes.NotesEvent
import com.erkindilekci.textbook.feature_note.presentation.notes.NotesViewModel

@Composable
fun NotesScreenTopAppBar(viewModel: NotesViewModel) {
    val state = viewModel.state.collectAsState().value
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.your_notes),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.SemiBold
            )

            IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = stringResource(id = R.string.sort_note)
                )
            }
        }

        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(modifier = Modifier
                .fillMaxWidth(),
                //.padding(vertical = 16.dp),
                noteOrder = state.noteOrder,
                onOrderChange = { viewModel.onEvent(NotesEvent.Order(it)) })

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}