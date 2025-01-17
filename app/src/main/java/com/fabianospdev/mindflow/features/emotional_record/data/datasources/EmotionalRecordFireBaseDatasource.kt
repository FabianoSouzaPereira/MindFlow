package com.fabianospdev.mindflow.features.emotional_record.data.datasources

import com.fabianospdev.mindflow.features.emotional_record.data.models.EmotionalRecordResponseModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class EmotionalRecordFireBaseDatasource @Inject constructor(
    private val firebaseAuth: FirebaseAuth?
) : EmotionalRecordDatasource {

    override suspend fun getEmotionalRecordContent(): EmotionalRecordResponseModel {
        TODO("Not yet implemented")
    }
}