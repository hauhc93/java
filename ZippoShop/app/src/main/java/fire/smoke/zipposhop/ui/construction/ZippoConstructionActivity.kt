package fire.smoke.zipposhop.ui.construction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fire.smoke.zipposhop.databinding.ActivityZippoConstructionBinding

class ZippoConstructionActivity : AppCompatActivity() {

    private var _binding: ActivityZippoConstructionBinding? = null
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
        _binding = ActivityZippoConstructionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}