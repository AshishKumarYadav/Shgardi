package com.ashish.shgardi.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.shgardi.data.model.People
import com.ashish.shgardi.domain.usecase.GetPopularPeopleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase,
    private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {


    // uses flow to get the data from use case and pass to the view
    private var currentPage = 1
    var isLastPage = false
    var isLoading = false

    val _popularPeopleList = MutableStateFlow<List<People?>?>(emptyList())
    val popularPeopleList: StateFlow<List<People?>?> = _popularPeopleList

    //to store the selected person
    var selectedPerson :People = People()

    private val _filteredPeopleList = MutableLiveData<List<People>>()
    val filteredPeopleList: LiveData<List<People>> get() = _filteredPeopleList

    fun fetchPopularPeopleList() {
        if (isLoading || isLastPage) return
        isLoading = true
        viewModelScope.launch(ioDispatcher) {
            // get the data from use case
            val data = getPopularPeopleListUseCase(currentPage)
            val temp = data.results?.filterNotNull().orEmpty()
            _popularPeopleList.value = _popularPeopleList.value?.plus(temp)

            // for pagination
            currentPage++
            isLoading = false
            isLastPage = data.page == data.totalPages
        }
    }

    fun searchPeople(query: String) {
        println("Query: $query")
        val filteredList = _popularPeopleList.value?.filter {
            it?.name?.contains(query, ignoreCase = true) == true
        }
        filteredList?.filterNotNull()?.let { _filteredPeopleList.postValue(it) }

    }

    override fun onCleared() {
        super.onCleared()
    }


}