package com.erkindilekci.textbook.domain.usecase

import com.erkindilekci.textbook.data.repository.FakeNoteRepository
import com.erkindilekci.textbook.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepository)
        getNoteUseCase = GetNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Get notes by id, returns note`() = runBlocking {
        val note = Note(
            title = "title",
            content = "content",
            timeStamp = 24L,
            color = 1234,
            id = 1
        )

        addNoteUseCase(note)

        assertThat(getNoteUseCase(note.id ?: 0)).isEqualTo(note)
    }
}
