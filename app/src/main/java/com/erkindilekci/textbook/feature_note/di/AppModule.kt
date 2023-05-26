package com.erkindilekci.textbook.feature_note.di

import android.app.Application
import androidx.room.Room
import com.erkindilekci.textbook.feature_note.data.datasource.NoteDatabase
import com.erkindilekci.textbook.feature_note.data.repository.NoteRepositoryImpl
import com.erkindilekci.textbook.feature_note.domain.repository.NoteRepository
import com.erkindilekci.textbook.feature_note.domain.usecase.AddNoteUseCase
import com.erkindilekci.textbook.feature_note.domain.usecase.DeleteNoteUseCase
import com.erkindilekci.textbook.feature_note.domain.usecase.GetNoteUseCase
import com.erkindilekci.textbook.feature_note.domain.usecase.GetNotesUseCase
import com.erkindilekci.textbook.feature_note.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(database: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(database.noteDao)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases = NoteUseCases(
        getNotesUseCase = GetNotesUseCase(repository),
        deleteNoteUseCase = DeleteNoteUseCase(repository),
        addNoteUseCase = AddNoteUseCase(repository),
        getNoteUseCase = GetNoteUseCase(repository)
    )
}
