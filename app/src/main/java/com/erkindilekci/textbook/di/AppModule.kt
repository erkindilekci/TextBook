package com.erkindilekci.textbook.di

import android.app.Application
import androidx.room.Room
import com.erkindilekci.textbook.data.datasource.NoteDatabase
import com.erkindilekci.textbook.data.repository.NoteRepositoryImpl
import com.erkindilekci.textbook.domain.repository.NoteRepository
import com.erkindilekci.textbook.domain.usecase.AddNoteUseCase
import com.erkindilekci.textbook.domain.usecase.DeleteNoteUseCase
import com.erkindilekci.textbook.domain.usecase.GetNoteUseCase
import com.erkindilekci.textbook.domain.usecase.GetNotesUseCase
import com.erkindilekci.textbook.domain.usecase.NoteUseCases
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
