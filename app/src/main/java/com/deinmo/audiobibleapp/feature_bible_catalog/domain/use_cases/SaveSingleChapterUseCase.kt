package com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import javax.inject.Inject

class SaveSingleChapterUseCase @Inject constructor(
    val repository: BibleDataRepository
){
}