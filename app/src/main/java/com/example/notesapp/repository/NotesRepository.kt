package com.example.notesapp.repository

import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface NotesRepository {
    suspend fun getNoteList(): Flow<Result<List<Note>>>
    suspend fun getNoteDetail(id: Int): Flow<Result<Note>>
    suspend fun delete(vararg notes: Note): Flow<Result<Boolean>>
    suspend fun update(vararg notes: Note): Flow<Result<Boolean>>
    suspend fun insert(note: Note): Flow<Result<Boolean>>
}

@Module
@InstallIn(SingletonComponent::class)
object NotesRepositoryModule {

    @Provides
    @Singleton
    fun provideNotesRepository(noteDao: NoteDao): NotesRepository {
        return NotesRepositoryImpl(noteDao)
    }
}