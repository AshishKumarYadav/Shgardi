package com.ashish.shgardi.data.remote

import com.ashish.delivery.data.remote.Endpoints.END_POINT_POPULAR
import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.utils.Resources
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET(END_POINT_POPULAR)
    suspend fun getPopularPeopleByPage(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Flow<Resources<PeopleList>>

}