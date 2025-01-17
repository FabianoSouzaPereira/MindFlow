package com.fabianospdev.mindflow.features.emotional_record.data.datasources

import com.fabianospdev.mindflow.features.emotional_record.data.models.EmotionalRecordResponseModel
import javax.inject.Inject

class EmotionalRecordRemoteDatasource @Inject constructor(
    private val retrofitService: EmotionalRecordDatasource
) : EmotionalRecordDatasource {

    override suspend fun getEmotionalRecordContent(): EmotionalRecordResponseModel {
        return retrofitService.getEmotionalRecordContent()
    }
}