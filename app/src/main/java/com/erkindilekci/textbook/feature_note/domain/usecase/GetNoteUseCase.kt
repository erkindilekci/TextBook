package com.erkindilekci.textbook.feature_note.domain.usecase

import com.erkindilekci.textbook.feature_note.domain.model.Note
import com.erkindilekci.textbook.feature_note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repo: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repo.getNoteById(id)
    }
}