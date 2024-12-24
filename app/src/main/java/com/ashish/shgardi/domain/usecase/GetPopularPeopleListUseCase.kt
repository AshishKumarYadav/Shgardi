package com.ashish.shgardi.domain.usecase

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.domain.repository.HomeRepository
import javax.inject.Inject

class GetPopularPeopleListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(page: Int): PeopleList {
        return homeRepository.getPopularPeopleLists(page)
    }
}