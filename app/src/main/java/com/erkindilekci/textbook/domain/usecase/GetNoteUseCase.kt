package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.domain.model.Note
import com.erkindilekci.textbook.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repo: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repo.getNoteById(id)
    }
}
