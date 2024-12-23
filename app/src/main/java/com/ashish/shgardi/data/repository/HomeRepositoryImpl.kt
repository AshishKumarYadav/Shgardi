package com.ashish.shgardi.data.repository

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.data.remote.ApiService
import com.ashish.shgardi.domain.repository.HomeRepository
import com.ashish.shgardi.utils.Resources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {

    override suspend fun getPopularPeopleLists(page:Int): Flow<Resources<PeopleList>> {
        return try {
            val response = apiService.getPopularPeopleByPage(page = page)
            response
        }catch (e:Exception){
            flow {
                emit(Resources.Error("An error occurred", 404))
            }
        }
    }
}