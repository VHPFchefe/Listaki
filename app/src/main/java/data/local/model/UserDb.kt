package data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDb(
    @ColumnInfo(name = "id_user")
    @PrimaryKey(autoGenerate = true)
    val idUser: Long?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "photo_url")
    val photoUrl: String?
)
