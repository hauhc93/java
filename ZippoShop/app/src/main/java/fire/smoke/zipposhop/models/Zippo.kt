package fire.smoke.zipposhop.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_zippo")
data class Zippo(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var src: Int? = null,
    var name: String? = null,
    var price: String? = null,
    var description: String? = null,
    var rank: Float,
)
