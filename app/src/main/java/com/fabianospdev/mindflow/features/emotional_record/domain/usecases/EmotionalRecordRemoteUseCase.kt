package com.fabianospdev.mindflow.features.emotional_record.domain.usecases

import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity

interface EmotionalRecordRemoteUseCase {
    suspend fun getEmotionalRecordContent(): Result<EmotionalRecordResponseEntity>
}