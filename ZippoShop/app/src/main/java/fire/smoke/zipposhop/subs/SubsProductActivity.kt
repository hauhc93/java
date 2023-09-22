package fire.smoke.zipposhop.subs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import fire.smoke.zipposhop.database.DataBaseHelper.insertZippo
import fire.smoke.zipposhop.databinding.ActivitySubsPurchaseBinding

class SubsProductActivity : AppCompatActivity() {

    private val mSkuDetailsMap: Map<String, SkuDetails> = HashMap()
    private val mSkuIdSub1 = ZippoSubsConstant.ITEM_TO_BUY_SKU_ID_1
    private val mSkuIdSub2 = ZippoSubsConstant.ITEM_TO_BUY_SKU_ID_2
    private val mSkuIdSub3 = ZippoSubsConstant.ITEM_TO_BUY_SKU_ID_3
    private val mSkuIdSub4 = ZippoSubsConstant.ITEM_TO_BUY_SKU_ID_4
    private val mSkuIdSub5 = ZippoSubsConstant.ITEM_TO_BUY_SKU_ID_5

    private var skuPurchase: String? = null
    private var mBillingClient: BillingClient? = null
    private var _binding: ActivitySubsPurchaseBinding? = null
    private val binding: ActivitySubsPurchaseBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        _binding = ActivitySubsPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SubsSessionManager.getInstance().init(this)

        initBilling()
        binding.btnBuy1.setOnClickListener { view: View? -> launchBilling(mSkuIdSub1) }
        binding.btnBuy2.setOnClickListener { view: View? -> launchBilling(mSkuIdSub2) }
        binding.btnBuy3.setOnClickListener { view: View? -> launchBilling(mSkuIdSub3) }
        binding.btnBuy4.setOnClickListener { view: View? -> launchBilling(mSkuIdSub4) }
        binding.btnBuy5.setOnClickListener { view: View? -> launchBilling(mSkuIdSub5) }
    }

    override fun onResume() {
        super.onResume()
        if (mBillingClient != null) {
            mBillingClient!!.queryPurchasesAsync(BillingClient.SkuType.SUBS) { billingResult: BillingResult, purchases: List<Purchase>? ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    for (i in purchases.indices) {
                        val purchase = purchases[i]
                        val consumeParams = ConsumeParams.newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()
                        mBillingClient!!.consumeAsync(consumeParams) { billingResult1: BillingResult, s: String? ->
                            Log.d("xx", "consumeAsync: " + billingResult1.responseCode)
                            Toast.makeText(this, "Buy Succeed", Toast.LENGTH_SHORT).show()
                            insertZippo(this, skuPurchase!!)
                        }
                    }
                }
            }
        }
    }

    private fun initBilling() {
        mBillingClient = BillingClient
            .newBuilder(this)
            .enablePendingPurchases()
            .setListener { billingResult: BillingResult, purchases: List<Purchase>? ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    for (i in purchases.indices) {
                        val purchase = purchases[i]
                        val consumeParams = ConsumeParams.newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()
                        mBillingClient!!.consumeAsync(consumeParams) { billingResult1: BillingResult?, s: String? ->
                            Toast.makeText(this, "Buy Succeed", Toast.LENGTH_SHORT).show()
                            insertZippo(this, skuPurchase!!)
                        }
                    }
                }
            }.build()
        mBillingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                //here when something went wrong, e.g. no internet connection
            }

            override fun onBillingSetupFinished(billingResult: BillingResult) {}
        })
    }

    fun launchBilling(skuId: String) {
        if (mBillingClient != null) {
            val skus: MutableList<String> = ArrayList()
            skus.add(skuId)
            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skus).setType(BillingClient.SkuType.SUBS)
            mBillingClient!!.querySkuDetailsAsync(params.build()) { billingResult: BillingResult?, list: List<SkuDetails?>? ->
                if (list != null && list.isNotEmpty()) {
                    skuPurchase = skuId
                    val flowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(list[0]!!)
                        .build()
                    mBillingClient!!.launchBillingFlow(this, flowParams)
                } else {
                    Toast.makeText(this, "Not found product", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}