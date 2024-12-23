package com.ashish.shgardi.domain.repository

import com.ashish.shgardi.data.model.PeopleList

interface HomeRepository {
    suspend fun getPopularPeopleLists(): List<PeopleList>
}