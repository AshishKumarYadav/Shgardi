package com.ashish.shgardi.domain.repository

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.utils.Resources
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPopularPeopleLists(page:Int): Flow<Resources<PeopleList>>
}