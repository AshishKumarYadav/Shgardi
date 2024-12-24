package com.ashish.shgardi.viemodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ashish.shgardi.data.model.People
import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.domain.usecase.GetPopularPeopleListUseCase
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPopularPeopleListUseCase: GetPopularPeopleListUseCase

    private lateinit var viewModel: MainViewModel
    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(getPopularPeopleListUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPopularPeopleList should update popularPeopleList on success`() = runTest {
        val peopleList = PeopleList(page = 1,
            results = listOf(
                People(name = "John Doe"),
                People(name = "Jane Doe")
            ),
            1,
            1
        )
        `when`(getPopularPeopleListUseCase.invoke(anyInt())).thenReturn(peopleList)

        viewModel.fetchPopularPeopleList()

        advanceUntilIdle()

        Assert.assertEquals(peopleList, viewModel.popularPeopleList.value)
    }

    @Test
    fun `searchPeople should update filteredPeopleList`() = runTest {
        val peopleList = listOf(People(name = "John Doe"), People(name = "Jane Doe"))
        viewModel._popularPeopleList.value = peopleList

        viewModel.searchPeople("Jane")

        Assert.assertEquals(listOf(People(name = "Jane Doe")), viewModel.filteredPeopleList.value)
    }
}