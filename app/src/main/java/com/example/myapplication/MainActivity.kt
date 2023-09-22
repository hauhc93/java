package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadWebView()
//
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }


    fun loadWebView(){

        binding.webView.webViewClient = object : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val javascriptCode = "function f_submit() {\n" +
                        "\n" +
                        "    var order = new Order();\n" +
                        "    order.pay_method            = \"\";\n" +
                        "    order.merchant_id           = \"30132\";\n" +
                        "    order.service_id            = \"001\";\n" +
                        "    order.cust_code             = \"Merchant_TestUser_999999\";\n" +
                        "    order.sps_cust_no           = \"\";\n" +
                        "    order.sps_payment_no        = \"\";\n" +
                        "    order.order_id              = \"5e48345bdec548d582d9abc873b4a70d\";\n" +
                        "    order.item_id               = \"T_0003\";\n" +
                        "    order.pay_item_id           = \"\";\n" +
                        "    order.item_name             = \"テスト商品\";\n" +
                        "    order.tax                   = \"\";\n" +
                        "    order.amount                = \"1\";\n" +
                        "    order.pay_type              = \"0\";\n" +
                        "    order.auto_charge_type      = \"\";\n" +
                        "    order.service_type          = \"0\";\n" +
                        "    order.div_settele           = \"\";\n" +
                        "    order.last_charge_month     = \"\";\n" +
                        "    order.camp_type             = \"\";\n" +
                        "    order.tracking_id           = \"\";\n" +
                        "    order.terminal_type         = \"0\";\n" +
                        "    order.success_url           = \"http://stbfep.sps-system.com/MerchantPaySuccess.jsp\";\n" +
                        "    order.cancel_url            = \"http://stbfep.sps-system.com/MerchantPayCancel.jsp\";\n" +
                        "    order.error_url             = \"http://stbfep.sps-system.com/MerchantPayError.jsp\";\n" +
                        "    order.pagecon_url           = \"http://stbfep.sps-system.com/MerchantPayResultRecieveSuccess.jsp\";\n" +
                        "    order.free1                 = \"\";\n" +
                        "    order.free2                 = \"\";\n" +
                        "    order.free3                 = \"\";\n" +
                        "    order.free_csv_input        =\n" +
                        "        \"LAST_NAME=鈴木,FIRST_NAME=太郎,LAST_NAME_KANA=スズキ,FIRST_NAME_KANA=タロウ,FIRST_ZIP=210,SECOND_ZIP=0001,ADD1=岐阜県,ADD2=あああ市あああ町,ADD3=,TEL=12345679801,MAIL=aaaa@bb.jp,ITEM_NAME=TEST ITEM\";\n" +
                        "    order.request_date          = \"20230922113332\";\n" +
                        "    order.limit_second          = \"\";\n" +
                        "    order.hashkey               = \"c48e0e2c7d04f0954594f14c7801bd430ca6263e\";\n" +
                        "\n" +
                        "    var orderDetail = new OrderDetail();\n" +
                        "    orderDetail.dtl_rowno       = \"1\";\n" +
                        "    orderDetail.dtl_item_id     = \"dtlItem_1\";\n" +
                        "    orderDetail.dtl_item_name   = \"明細商品名1\";\n" +
                        "    orderDetail.dtl_item_count  = \"1\";\n" +
                        "    orderDetail.dtl_tax         = \"1\";\n" +
                        "    orderDetail.dtl_amount      = \"1\";\n" +
                        "    orderDetail.dtl_free1       = \"\";\n" +
                        "    orderDetail.dtl_free2       = \"\";\n" +
                        "    orderDetail.dtl_free3       = \"\";\n" +
                        "    order.orderDetail.push(orderDetail);\n" +
                        "\n" +
                        "    // フリーCSV\n" +
                        "    order.free_csv              = base64.encode(order.free_csv_input, 1);\n" +
                        "\n" +
                        "    //チェックサム\n" +
                        "    order.sps_hashcode          = Sha1.hash( order.toString() );\n" +
                        "\n" +
                        "    feppost(order);\n" +
                        "}\n"
                binding.webView.evaluateJavascript(javascriptCode, ValueCallback<String?> { value ->
                    Log.d("TAG11","JAVA RUN")
                })
                binding.webView.loadUrl("javascript:myFunction();")
            }
        }
        binding.webView.loadUrl("https://stbfep.sps-system.com/f01/FepBuyInfoReceive.do")
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}