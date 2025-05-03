package com.example.notesapp.repository

import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val dao: NoteDao
): NotesRepository {
    override suspend fun getNoteList(): Flow<Result<List<Note>>> =
        dao.getNotes().map {
            Result.success(it)
        }.catch {
            emit(Result.failure(IOException("Failed to fetch notes list")))
        }


    override suspend fun getNoteDetail(id: Int): Flow<Result<Note>> =
        dao.getNoteDetail(id).map {
            Result.success(it)
        }.catch {
            emit(Result.failure(IOException("Failed to fetch note with id $id")))
        }

    override suspend fun delete(vararg notes: Note): Flow<Result<Boolean>> =
        flow {
            val result: Boolean = (dao.delete(*notes) == notes.size)
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(IOException("Failed to delete Notes $notes")))
        }

    override suspend fun update(vararg notes: Note): Flow<Result<Boolean>> =
        flow {
            val result: Boolean = (dao.update(*notes) == notes.size)
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(IOException("Unable to update notes list")))
        }


    override suspend fun insert(note: Note): Flow<Result<Boolean>> =
        flow {
            val result: Boolean = (dao.insert(note) != 1L)
            emit(Result.success(result))
        }.catch {
            emit(Result.failure(IOException("Unable to insert note")))
        }

}