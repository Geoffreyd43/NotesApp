package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notesapp.data.Note
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
object NotesList
@Serializable
data class NoteDetailRoute(val id: Int)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                NotesNav(Modifier)
            }
        }
    }
}

@Composable
fun NotesNav(modifier: Modifier) {
    val viewModel: NotesViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NotesList) {
        composable<NotesList> { NotesList(
            modifier,
            selectNoteDetail = { noteId: Int ->
                navController.navigate(route = NoteDetailRoute(id = noteId))
            },
            viewModel) }
        composable<NoteDetailRoute> { backStackEntry ->
            val noteDetail: NoteDetailRoute = backStackEntry.toRoute()
            NoteDetail(modifier, noteDetail.id, viewModel)
        }
    }
}

@Composable
fun NotesList(modifier: Modifier = Modifier,
              selectNoteDetail: (Int) -> Unit,
              viewModel: NotesViewModel) {
    val notesList: List<Note> = viewModel.getNotes()

    LazyColumn {
        items(notesList) { note ->
            Button(onClick = { selectNoteDetail(note.id) }) {
                Text(note.title)
            }
        }
    }
}

@Composable
fun NoteDetail(
    modifier: Modifier,
    id: Int,
    viewModel: NotesViewModel) {
    val note: Note = viewModel.getNote(id)

    Column() {
        Text(note.title)
        Text(note.content)
    }
}


@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    NotesAppTheme {
        NotesNav(Modifier)
    }
}