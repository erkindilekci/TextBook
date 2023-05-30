package com.erkindilekci.textbook.presentation.notes

import com.erkindilekci.textbook.domain.model.Note
import com.erkindilekci.textbook.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
