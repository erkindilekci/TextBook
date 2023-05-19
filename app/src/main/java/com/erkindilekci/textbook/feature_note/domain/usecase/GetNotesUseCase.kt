package com.erkindilekci.textbook.feature_note.domain.usecase

import com.erkindilekci.textbook.feature_note.domain.model.Note
import com.erkindilekci.textbook.feature_note.domain.repository.NoteRepository
import com.erkindilekci.textbook.feature_note.domain.util.NoteOrder
import com.erkindilekci.textbook.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repo: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repo.getNotes().map { noteList ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> noteList.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> noteList.sortedBy { it.timeStamp }
                        is NoteOrder.Color -> noteList.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> noteList.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> noteList.sortedByDescending { it.timeStamp }
                        is NoteOrder.Color -> noteList.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}