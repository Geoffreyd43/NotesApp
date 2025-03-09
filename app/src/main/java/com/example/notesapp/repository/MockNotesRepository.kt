package com.example.notesapp.repository

import com.example.notesapp.data.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    override fun getNotes(): List<Note> {
        return listOf(
            todo,
            shoppingList,
            passwords
        )
    }

    override fun getNote(id: Int): Note {
        return when (id) {
            1 -> todo
            2 -> shoppingList
            3 -> passwords
            else -> Note(id = 0)
        }

    }
}

@Module
@InstallIn(SingletonComponent::class)
object NotesRepositoryModule {

    @Provides
    @Singleton
    fun provideNotesRepository(): NotesRepository {
        return MockNotesRepository()
    }
}
