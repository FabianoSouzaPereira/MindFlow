package com.fabianospdev.mindflow.features.emotional_record.data.models

import com.fabianospdev.mindflow.features.emotional_record.domain.entities.EmotionalRecordResponseEntity

data class EmotionalRecordResponseModel(
    override val id: String,
    override val emotionType: String,
    override val intensity: Int = 5,
    override val timestamp: Long = System.currentTimeMillis(),
    override val notes: String? = "",
    override val location: String? = "",
    override val trigger: String? = "",
    override val category: String? = "neutral",
    override val duration: Int? = 1,
    override val tags: List<String>? = emptyList()
) : EmotionalRecordResponseEntity