package com.ashish.shgardi.data.repository

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.data.remote.ApiService
import com.ashish.shgardi.domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {

    override suspend fun getPopularPeopleLists(page:Int): PeopleList {
        return apiService.getPopularPeopleByPage(page = page)

    }
}