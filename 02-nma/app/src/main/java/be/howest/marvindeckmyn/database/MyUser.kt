package be.howest.marvindeckmyn.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class MyUser(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "username")
    val username: String
)
