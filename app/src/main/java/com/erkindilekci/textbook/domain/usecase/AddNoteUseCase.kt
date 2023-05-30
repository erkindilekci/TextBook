package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.domain.model.InvalidNoteException
import com.erkindilekci.textbook.domain.model.Note
import com.erkindilekci.textbook.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repo: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.trim().isBlank()) {
            throw InvalidNoteException("The title can't be empty!")
        }
        if (note.content.trim().isBlank()) {
            throw InvalidNoteException("The content can't be empty!")
        }

        repo.insertNote(note)
    }
}
