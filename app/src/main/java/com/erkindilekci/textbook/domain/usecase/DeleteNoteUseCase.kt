package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.domain.model.Note
import com.erkindilekci.textbook.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repo: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repo.deleteNote(note)
    }
}
