package com.erkindilekci.textbook.presentation.addeditnote

data class NoteTextFieldsState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)
