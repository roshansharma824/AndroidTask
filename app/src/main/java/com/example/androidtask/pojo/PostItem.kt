package com.example.androidtask.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostItem")
data class PostItem(
    val body: String,
    @PrimaryKey @ColumnInfo(name = "id")val id: Int,
    val title: String,
    val userId: Int
)