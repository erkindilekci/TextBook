package com.erkindilekci.textbook.presentation.notes

import com.erkindilekci.textbook.domain.model.Note
import com.erkindilekci.textbook.domain.util.NoteOrder
import com.erkindilekci.textbook.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
