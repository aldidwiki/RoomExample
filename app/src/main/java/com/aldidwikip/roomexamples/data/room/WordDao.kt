package com.aldidwikip.roomexamples.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldidwikip.roomexamples.data.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("UPDATE word_table SET name = :name, gender = :gender, job = :job, city = :city WHERE id = :id")
    suspend fun update(id: Int, name: String, gender: String, job: String, city: String)
}