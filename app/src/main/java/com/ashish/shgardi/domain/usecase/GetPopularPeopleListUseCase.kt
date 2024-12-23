package com.ashish.shgardi.domain.usecase

import com.ashish.shgardi.data.model.PeopleList
import com.ashish.shgardi.domain.repository.HomeRepository
import com.ashish.shgardi.utils.Resources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularPeopleListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(page: Int): Flow<Resources<PeopleList>> {
        return homeRepository.getPopularPeopleLists(page)
    }
}