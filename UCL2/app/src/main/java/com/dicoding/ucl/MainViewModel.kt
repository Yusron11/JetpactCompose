package com.dicoding.ucl

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.ucl.data.ClubRepository
import com.dicoding.ucl.model.Club
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalArgumentException

class MainViewModel (private val repository: ClubRepository) : ViewModel() {
    private val _groupedClub = MutableStateFlow(
        repository.getClub()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedClub: StateFlow<Map<Char, List<Club>>> get() = _groupedClub

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedClub.value = repository.searchClub(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }

    fun getClubById(id: String): Club {
        return repository.getClub().firstOrNull { it.id == id }
            ?: throw NoSuchElementException("Club with id $id not found")
    }

}

class ViewModelFactory (private val repository: ClubRepository) :
        ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKES_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException ("Unknown ViewModel class: " + modelClass.name)
    }
}