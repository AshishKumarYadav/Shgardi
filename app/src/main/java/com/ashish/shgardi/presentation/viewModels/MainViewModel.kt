package com.ashish.shgardi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.domain.usecase.GetPopularPeopleListUseCase
import com.ashish.shgardi.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase,
    ioDispatcher: CoroutineDispatcher,
): ViewModel() {


    // uses flow to get the data from use case and pass to the view
    private var currentPage = 1
    var isLastPage = false
    var isLoading = false

    private val _popularPeopleList = MutableStateFlow<PeopleList>(PeopleList())
    val popularPeopleList: StateFlow<PeopleList> = _popularPeopleList


    fun fetchPopularPeopleList() {
        if (isLoading || isLastPage) return
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPopularPeopleListUseCase(currentPage)
            println("result: $result")
            _popularPeopleList.value = result
            currentPage++
            isLoading = false
            if (result.page == result.totalPages) {
                isLastPage = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}