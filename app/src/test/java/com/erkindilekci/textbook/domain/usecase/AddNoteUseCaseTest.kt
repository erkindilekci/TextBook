package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.data.repository.FakeNoteRepository
import com.erkindilekci.textbook.domain.model.InvalidNoteException
import com.erkindilekci.textbook.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty title, throws exception`() = runBlocking {
        val noteToInsert = Note(
            title = "",
            content = "content",
            timeStamp = 24L,
            color = 1234
        )

        addNoteUseCase(noteToInsert)
    }

    @Test(expected = InvalidNoteException::class)
    fun `Add note with empty content, throws exception`() = runBlocking {
        val noteToInsert = Note(
            title = "title",
            content = "",
            timeStamp = 24L,
            color = 1234
        )

        addNoteUseCase(noteToInsert)
    }

    @Test
    fun `Add note with a title and content, returns true`() = runBlocking {
        val noteToInsert = Note(
            title = "title",
            content = "content",
            timeStamp = 24L,
            color = 1234,
            id = 2
        )

        fakeNoteRepository.deleteNote(noteToInsert)
        addNoteUseCase(noteToInsert)

        assertThat(fakeNoteRepository.getNoteById(noteToInsert.id ?: 0)).isEqualTo(noteToInsert)
    }
}
