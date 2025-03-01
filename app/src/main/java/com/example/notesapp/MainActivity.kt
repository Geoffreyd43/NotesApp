package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.data.Note
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                NotesList(Modifier)
            }
        }
    }
}

@Composable
fun NotesList(modifier: Modifier = Modifier) {
    val viewModel: NotesViewModel = viewModel()
    val notesList: List<Note> = viewModel.getNotes()

    LazyColumn {
        items(notesList) { note ->
            Text(note.title)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    NotesAppTheme {
        NotesList(Modifier)
    }
}