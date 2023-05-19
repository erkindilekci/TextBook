package com.erkindilekci.textbook.feature_note.presentation.addeditnote

data class NoteTextFieldsState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)