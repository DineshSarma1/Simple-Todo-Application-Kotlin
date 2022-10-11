package com.example.daggerhiltmvvplayouttodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var title: String,
    var description: String?,
    var isDone: Boolean,
    @PrimaryKey var id: Int? = null
)