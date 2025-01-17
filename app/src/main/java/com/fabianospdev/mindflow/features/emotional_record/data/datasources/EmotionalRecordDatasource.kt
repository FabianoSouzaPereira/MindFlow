package com.fabianospdev.mindflow.features.emotional_record.data.datasources

import com.fabianospdev.mindflow.features.emotional_record.data.models.EmotionalRecordResponseModel

interface EmotionalRecordDatasource {
    suspend fun getEmotionalRecordContent(): EmotionalRecordResponseModel
}