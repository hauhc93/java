package fire.smoke.zipposhop.ui.photo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fire.smoke.zipposhop.R
import fire.smoke.zipposhop.databinding.ActivityImageDetailBinding

class PhotoDetailViewActivity : AppCompatActivity() {

    companion object {
        const val KEY_INTENT_DETAIL = "IMG_ZIPPO_DETAIL"
    }

    private var _binding: ActivityImageDetailBinding? = null
    private val binding: ActivityImageDetailBinding get() = _binding!!
    private var imgResource: Int = R.drawable.zippo_1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        intent.extras?.let {
            imgResource = it.getInt(KEY_INTENT_DETAIL)
        }
        loadImage()
    }

    private fun loadImage() {
        binding.photoView.setImageResource(imgResource)
    }
}