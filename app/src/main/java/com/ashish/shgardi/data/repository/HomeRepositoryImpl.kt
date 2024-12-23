package com.ashish.shgardi.data.repository

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.data.remote.ApiService
import com.ashish.shgardi.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService,
) : HomeRepository {

    override suspend fun getPopularPeopleLists(): List<PeopleList> {
        TODO("Not yet implemented")
    }
}