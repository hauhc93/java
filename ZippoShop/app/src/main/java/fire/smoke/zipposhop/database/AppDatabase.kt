package fire.smoke.zipposhop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fire.smoke.zipposhop.models.Zippo

@Database(entities = [Zippo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context): ZippoDAO? =
            Room.databaseBuilder(context, AppDatabase::class.java, "db_zippo")
                .allowMainThreadQueries() //Allows room to do operation on main thread
                .build()
                .zippoDAO
    }

    abstract val zippoDAO: ZippoDAO?
}