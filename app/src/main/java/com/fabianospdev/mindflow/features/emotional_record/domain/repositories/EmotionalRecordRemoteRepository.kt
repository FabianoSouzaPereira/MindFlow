package com.fabianospdev.mindflow.features.emotional_record.domain.repositories

import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity

interface EmotionalRecordRemoteRepository {
    suspend fun getEmotionalRecordContent(): Result<EmotionalRecordResponseEntity>
}