package fire.smoke.zipposhop.ui.inapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fire.smoke.zipposhop.MainActivity;
import fire.smoke.zipposhop.R;
import fire.smoke.zipposhop.database.DataBaseHelper;
import fire.smoke.zipposhop.subs.SubsProductActivity;

public class InAppProductActivity extends AppCompatActivity {

    private final Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    private final String mSkuIdSub1 = ZippoConstant.ITEM_TO_BUY_SKU_ID_1;
    private final String mSkuIdSub2 = ZippoConstant.ITEM_TO_BUY_SKU_ID_2;
    private final String mSkuIdSub3 = ZippoConstant.ITEM_TO_BUY_SKU_ID_3;
    private final String mSkuIdSub4 = ZippoConstant.ITEM_TO_BUY_SKU_ID_4;
    private final String mSkuIdSub5 = ZippoConstant.ITEM_TO_BUY_SKU_ID_5;
    private String skuPurchase;
    private BillingClient mBillingClient;
    private Button btnBuy1, btnBuy2, btnBuy3, btnBuy4, btnBuy5, btnVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_inapp_purchase);

        SessionManager.getInstance().init(this);

        btnBuy1 = findViewById(R.id.btn_buy_1);
        btnBuy2 = findViewById(R.id.btn_buy_2);
        btnBuy3 = findViewById(R.id.btn_buy_3);
        btnBuy4 = findViewById(R.id.btn_buy_4);
        btnBuy5 = findViewById(R.id.btn_buy_5);
        btnVip = findViewById(R.id.btn_vip);

        initBilling();

        btnBuy1.setOnClickListener(view -> launchBilling(mSkuIdSub1));
        btnBuy2.setOnClickListener(view -> launchBilling(mSkuIdSub2));
        btnBuy3.setOnClickListener(view -> launchBilling(mSkuIdSub3));
        btnBuy4.setOnClickListener(view -> launchBilling(mSkuIdSub4));
        btnBuy5.setOnClickListener(view -> launchBilling(mSkuIdSub5));
        btnVip.setOnClickListener(view ->
                startActivity(
                        new Intent(
                                InAppProductActivity.this,
                                SubsProductActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBillingClient != null) {
            mBillingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, (billingResult, purchases) -> {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                    for (int i = 0; i < purchases.size(); i++) {
                        Purchase purchase = purchases.get(i);
                        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                        mBillingClient.consumeAsync(consumeParams, (billingResult1, s) -> {
                            Log.d("xx", "consumeAsync: " + billingResult1.getResponseCode());
                            Toast.makeText(this, "Buy Succeed", Toast.LENGTH_SHORT).show();
                            DataBaseHelper.INSTANCE.insertZippo(this, skuPurchase);
                        });
                    }
                }
            });
        }
    }

    private void initBilling() {
        mBillingClient = BillingClient
                .newBuilder(this)
                .enablePendingPurchases()
                .setListener((billingResult, purchases) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                        for (int i = 0; i < purchases.size(); i++) {
                            Purchase purchase = purchases.get(i);
                            ConsumeParams consumeParams = ConsumeParams.newBuilder()
                                    .setPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                            mBillingClient.consumeAsync(consumeParams, (billingResult1, s) -> {
                                Toast.makeText(this, "Buy Succeed", Toast.LENGTH_SHORT).show();
                                DataBaseHelper.INSTANCE.insertZippo(this, skuPurchase);
                            });
                        }
                    }
                }).build();

        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                //here when something went wrong, e.g. no internet connection
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
            }
        });
    }

    public void launchBilling(String skuId) {
        if (mBillingClient != null) {
            List<String> skus = new ArrayList<>();
            skus.add(skuId);
            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
            params.setSkusList(skus).setType(BillingClient.SkuType.INAPP);
            mBillingClient.querySkuDetailsAsync(params.build(), (billingResult, list) -> {
                if (list != null && list.size() > 0) {
                    skuPurchase = skuId;
                    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                            .setSkuDetails(list.get(0))
                            .build();
                    mBillingClient.launchBillingFlow(this, flowParams);
                } else {
                    Toast.makeText(this, "Not found product", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(InAppProductActivity.this, MainActivity.class).putExtra("requestSystemAlert", true).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}