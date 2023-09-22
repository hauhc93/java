package fire.smoke.zipposhop.ui.address

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fire.smoke.zipposhop.databinding.ActivityInfoBinding

class AddressInfoActivity : AppCompatActivity() {

    private var _binding: ActivityInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}