package com.example.androidtask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtask.pojo.PostItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT *FROM  POSTITEM")
    fun getAllPosts(): Flow<List<PostItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postItem: PostItem)


    @Query("DELETE FROM POSTITEM")
    suspend fun deleteAll()
}