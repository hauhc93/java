package fire.smoke.zipposhop.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fire.smoke.zipposhop.models.Zippo

@Dao
interface ZippoDAO {
    @Insert
    fun insertZippo(vararg zippo: Zippo?)

    @Update
    fun updateZippo(vararg zippo: Zippo?)

    @Delete
    fun deleteZippo(zippo: Zippo?)

    @Query("DELETE FROM table_zippo")
    fun deleteTable()

    @get:Query("SELECT * FROM table_zippo")
    val zippos: List<Zippo?>?
}
