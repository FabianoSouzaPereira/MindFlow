package com.fabianospdev.mindflow.features.emotional_record.domain.entities
/*
*  id: String - Already present, to uniquely identify each record.
*  emotionType: String - To indicate the type of emotion (e.g., happiness, sadness, anger).
*  intensity: Int - To represent the intensity of the emotion on a scale, for example, from 1 to 10.
*  timestamp: Long - To store the time at which the record was created.
*  notes: String? - Optional field for additional notes about what may have caused the emotion or how the person was feeling.
*  location: String? - Optional field to record the location where the emotion was felt, if relevant.
*  trigger: String? - Optional field to indicate what may have triggered the emotion.
*  category: String? - To group emotions into categories, such as "positive", "negative", or "neutral".
*  duration: Int? - To record the duration of the emotion in minutes, if applicable.
*  tags: List<String>? - To associate tags that help in categorizing or later searching for records.
*/


interface EmotionalRecordResponseEntity {
    val id: String
    val emotionType: String
    val intensity: Int
    val timestamp: Long
    val notes: String?
    val location: String?
    val trigger: String?
    val category: String?
    val duration: Int?
    val tags: List<String>?
}
