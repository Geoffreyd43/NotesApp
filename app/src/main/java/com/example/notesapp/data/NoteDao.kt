package com.example.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id == :id")
    fun getNoteDetail(id: Int): Flow<Note>

    @Query("SELECT * FROM note WHERE id IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): Flow<List<Note>>

    @Insert
    fun insert(note: Note): Long

    @Update
    fun update(vararg notes: Note): Int

    @Delete
    fun delete(vararg note: Note): Int
}