package com.aldidwikip.roomexamples.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "job") val job: String,
        @ColumnInfo(name = "city") val city: String
)