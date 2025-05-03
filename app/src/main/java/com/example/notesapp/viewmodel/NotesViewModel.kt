package com.example.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.notesapp.data.Note
import com.example.notesapp.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {

    private val noteListLiveData = MutableLiveData<Boolean>()
    private val noteDetailLiveData = MutableLiveData<Boolean>()

    fun getNoteList(): LiveData<Result<List<Note>>> = liveData {
        noteListLiveData.postValue(true)
        try {
            emitSource(notesRepository.getNoteList()
                .onEach {
                    noteListLiveData.postValue(false)
                }
                .asLiveData())
        } catch (e: Exception) {
            noteListLiveData.postValue(false)
            emit(Result.failure(e))
        }
    }

    fun getNoteDetail(id: Int): LiveData<Result<Note>> = liveData {
        noteDetailLiveData.postValue(true)
        try {
            emitSource(notesRepository.getNoteDetail(id)
                .onEach {
                    noteDetailLiveData.postValue(false)
                }
                .asLiveData())
        } catch (e: Exception) {
            noteDetailLiveData.postValue(false)
            emit(Result.failure(e))
        }
    }
}
