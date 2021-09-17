package be.howest.marvindeckmyn.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(myUser: MyUser)

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getMyUser(): MyUser
}