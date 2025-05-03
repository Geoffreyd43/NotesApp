package com.example.notesapp.repository

import com.example.notesapp.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockNotesRepository : NotesRepository {

    val todo = Note(
        id = 1,
        title = "Todo",
        content = "* kick butt\n* take names"
    )
    val shoppingList = Note(
        id = 2,
        title = "Shopping list",
        content = "Milk, eggs, cheese..."
    )
    val passwords = Note(
        id = 3,
        title = "Passwords",
        content = "Silly human, I would never keep my passwords here ;)"
    )

    override suspend fun getNoteList(): Flow<Result<List<Note>>> {
        return flow {
            listOf(
                todo,
                shoppingList,
                passwords
            )
        }
    }

    override suspend fun getNoteDetail(id: Int): Flow<Result<Note>> {
        return flow {
            when (id) {
                1 -> todo
                2 -> shoppingList
                3 -> passwords
                else -> Note(id = 0)
            }
        }

    }

    override suspend fun delete(vararg notes: Note): Flow<Result<Boolean>> {
        return flow {
            Result.success(true)
        }
    }

    override suspend fun update(vararg notes: Note): Flow<Result<Boolean>> {
        return flow {
            Result.success(true)
        }
    }

    override suspend fun insert(note: Note): Flow<Result<Boolean>> {
        return flow {
            Result.success(true)
        }
    }
}
