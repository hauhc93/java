package fire.smoke.zipposhop.ui.howtouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fire.smoke.zipposhop.databinding.ActivityZippoHowBinding

class ZippoHowToUseActivity : AppCompatActivity() {

    private var _binding: ActivityZippoHowBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {
        _binding = ActivityZippoHowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}