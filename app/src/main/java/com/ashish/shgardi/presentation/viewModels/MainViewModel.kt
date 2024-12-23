package com.ashish.shgardi.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.domain.usecase.GetPopularPeopleListUseCase
import com.ashish.shgardi.utils.Resources
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainViewModel @Inject constructor(
    private val getPopularPeopleListUseCase: GetPopularPeopleListUseCase,
    ioDispatcher: CoroutineDispatcher,
): ViewModel() {


    // uses flow to get the data from use case and pass to the view
    private var currentPage = 1
    var isLastPage = false
    var isLoading = false

    private val _popularPeopleList = MutableStateFlow<Resources<PeopleList>>(Resources.Loading())
    val popularPeopleList: StateFlow<Resources<PeopleList>> = _popularPeopleList


    fun fetchPopularPeopleList() {
        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            getPopularPeopleListUseCase.invoke(currentPage).collect { result ->
                if (result is Resources.Success) {
                    val peopleList = result.data
                    currentPage++
                    if (peopleList != null) {
                        isLastPage = peopleList.page == peopleList.totalPages
                        _popularPeopleList.value = Resources.Success(
                            PeopleList(
                                page = peopleList.page,
                                results = (_popularPeopleList.value as? Resources.Success)?.data?.results.orEmpty() + peopleList.results.orEmpty(),
                                totalPages = peopleList.totalPages,
                                totalResults = peopleList.totalResults
                            )
                        )
                    }
                } else {
                    _popularPeopleList.value = result
                }
                isLoading = false
            }
        }
    }


}