package com.erkindilekci.textbook.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.erkindilekci.textbook.R
import com.erkindilekci.textbook.feature_note.domain.util.NoteOrder
import com.erkindilekci.textbook.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = stringResource(id = R.string.title),
                selected = noteOrder is NoteOrder.Title,
                onSelected = { onOrderChange(NoteOrder.Title(orderType = noteOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(10.dp))

            DefaultRadioButton(
                text = stringResource(id = R.string.date),
                selected = noteOrder is NoteOrder.Date,
                onSelected = { onOrderChange(NoteOrder.Date(orderType = noteOrder.orderType)) }
            )

            Spacer(modifier = Modifier.width(10.dp))

            DefaultRadioButton(
                text = stringResource(id = R.string.color),
                selected = noteOrder is NoteOrder.Color,
                onSelected = { onOrderChange(NoteOrder.Color(orderType = noteOrder.orderType)) }
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = stringResource(id = R.string.ascending),
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelected = { onOrderChange(noteOrder.copy(OrderType.Ascending)) }
            )

            Spacer(modifier = Modifier.width(10.dp))

            DefaultRadioButton(
                text = stringResource(id = R.string.descending),
                selected = noteOrder.orderType is OrderType.Descending,
                onSelected = { onOrderChange(noteOrder.copy(OrderType.Descending)) }
            )
        }
    }
}
