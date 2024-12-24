package com.ashish.shgardi.data.remote

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.data.remote.Endpoints.END_POINT_POPULAR
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET(END_POINT_POPULAR)
    suspend fun getPopularPeopleByPage(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): PeopleList

}