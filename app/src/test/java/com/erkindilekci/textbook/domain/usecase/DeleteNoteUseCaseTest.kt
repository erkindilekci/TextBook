package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.data.repository.FakeNoteRepository
import com.erkindilekci.textbook.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUseCase = DeleteNoteUseCase(fakeNoteRepository)
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Delete note with note instance, returns true`() = runBlocking {
        val noteToDelete = Note(
            title = "title",
            content = "content",
            timeStamp = 24L,
            color = 1234,
            id = 1
        )

        addNoteUseCase(noteToDelete)
        deleteNoteUseCase(noteToDelete)

        val notes = fakeNoteRepository.getNotes().first()

        assertThat(notes).doesNotContain(noteToDelete)
    }
}
