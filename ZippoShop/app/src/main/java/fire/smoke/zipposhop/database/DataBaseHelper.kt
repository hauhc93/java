package fire.smoke.zipposhop.database

import android.content.Context
import fire.smoke.zipposhop.R
import fire.smoke.zipposhop.models.Zippo
import fire.smoke.zipposhop.ui.inapp.ZippoConstant

object DataBaseHelper {

    fun insertZippo(context: Context, skuPurchase: String) {
        AppDatabase.newInstance(context)?.insertZippo(generateObjectPurchase(skuPurchase))
    }

    fun updateZippo(context: Context, skuPurchase: String) {
        AppDatabase.newInstance(context)?.updateZippo(generateObjectPurchase(skuPurchase))
    }

    fun deleteZippo(context: Context) {
        AppDatabase.newInstance(context)?.deleteTable()
    }

    private fun generateObjectPurchase(skuId: String): Zippo {
        return when (skuId) {
            ZippoConstant.ITEM_TO_BUY_SKU_ID_1 -> Zippo(
                src = R.drawable.zippo_1,
                name = "Compass Zippo",
                price = "0.5$",
                description = "Custom zippo",
                rank = 4.3f
            )

            ZippoConstant.ITEM_TO_BUY_SKU_ID_2 -> Zippo(
                src = R.drawable.zippo_2,
                name = "Building Zippo",
                price = "1$",
                description = "Custom zippo",
                rank = 4.3f
            )

            ZippoConstant.ITEM_TO_BUY_SKU_ID_3 -> Zippo(
                src = R.drawable.zippo_3,
                name = "Weapon Zippo",
                price = "3$",
                description = "Custom zippo",
                rank = 4.6f
            )

            ZippoConstant.ITEM_TO_BUY_SKU_ID_4 -> Zippo(
                src = R.drawable.zippo_4,
                name = "Hell Zippo",
                price = "5$",
                description = "Custom zippo",
                rank = 4.8f
            )

            else -> Zippo(
                src = R.drawable.zippo_5,
                name = "Art Zippo",
                price = "10$",
                description = "Custom zippo",
                rank = 4.9f
            )
        }
    }
}
