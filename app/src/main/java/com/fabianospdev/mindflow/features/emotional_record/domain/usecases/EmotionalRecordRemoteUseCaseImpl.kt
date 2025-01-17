package com.fabianospdev.mindflow.features.emotional_record.domain.usecases

import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity
import com.fabianospdev.mindflow.features.emotional_record.domain.repositories.EmotionalRecordRemoteRepository
import javax.inject.Inject

class EmotionalRecordRemoteUseCaseImpl @Inject constructor(
    private val repository: EmotionalRecordRemoteRepository
) : EmotionalRecordRemoteUseCase {

    override suspend fun getEmotionalRecordContent(): Result<EmotionalRecordResponseEntity> {
        TODO("Not yet implemented")
    }
}