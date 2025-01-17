package com.fabianospdev.mindflow.features.emotional_record.data.repositories

import android.content.Context
import com.fabianospdev.mindflow.features.emotional_record.data.datasources.EmotionalRecordDatasource
import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity
import com.fabianospdev.mindflow.features.emotional_record.domain.repositories.EmotionalRecordRemoteRepository
import javax.inject.Inject

class EmotionalRecordRemoteRepositoryImpl @Inject constructor(
    private val emotionalRecordDatasource: EmotionalRecordDatasource,
    context: Context
) : EmotionalRecordRemoteRepository {

    override suspend fun getEmotionalRecordContent(): Result<EmotionalRecordResponseEntity> {
        return try {
            val response = emotionalRecordDatasource.getEmotionalRecordContent()
            return Result.success(response as EmotionalRecordResponseEntity)

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}