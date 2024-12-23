package com.ashish.shgardi.data.remote

import com.ashish.delivery.data.remote.Endpoints.END_POINT_POPULAR
import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.utils.Resources
import retrofit2.http.GET

interface ApiService {


    @GET(END_POINT_POPULAR)
    suspend fun getPopularPeopleByPage(): Resources<PeopleList>

}