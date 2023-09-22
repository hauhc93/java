package fire.smoke.zipposhop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fire.smoke.zipposhop.database.AppDatabase
import fire.smoke.zipposhop.databinding.ActivityMainBinding
import fire.smoke.zipposhop.models.Zippo
import fire.smoke.zipposhop.ui.adapter.HomeRecyclerViewAdapter
import fire.smoke.zipposhop.ui.address.AddressInfoActivity
import fire.smoke.zipposhop.ui.construction.ZippoConstructionActivity
import fire.smoke.zipposhop.ui.howtouse.ZippoHowToUseActivity
import fire.smoke.zipposhop.ui.inapp.InAppProductActivity
import fire.smoke.zipposhop.ui.list.ZippoListActivity
import fire.smoke.zipposhop.ui.photo.PhotoDetailViewActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private val adapter: HomeRecyclerViewAdapter by lazy { HomeRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initView() {
        val list = AppDatabase.newInstance(this)?.zippos
        if (list.isNullOrEmpty()) {
            AppDatabase.newInstance(this)?.insertZippo(
                Zippo(
                    src = R.drawable.zippo_1,
                    name = "Compass Zippo",
                    price = "0.5$",
                    description = "Custom zippo",
                    rank = 4.3f
                ),
                Zippo(
                    src = R.drawable.zippo_2,
                    name = "Building Zippo",
                    price = "1$",
                    description = "Custom zippo",
                    rank = 4.3f
                ),
                Zippo(
                    src = R.drawable.zippo_3,
                    name = "Weapon Zippo",
                    price = "3$",
                    description = "Custom zippo",
                    rank = 4.6f
                ),
                Zippo(
                    src = R.drawable.zippo_4,
                    name = "Hell Zippo",
                    price = "5$",
                    description = "Custom zippo",
                    rank = 4.8f
                ),
                Zippo(
                    src = R.drawable.zippo_5,
                    name = "Art Zippo",
                    price = "10$",
                    description = "Custom zippo",
                    rank = 4.9f
                ),
            )
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        adapter.submitList(getListData())
    }

    private fun initListeners() {
        adapter.onItemClick = {
            startActivity(
                Intent(this, PhotoDetailViewActivity::class.java).putExtra(
                    PhotoDetailViewActivity.KEY_INTENT_DETAIL, it
                )
            )
        }

        binding.btnBuy.setOnClickListener {
            startActivity(Intent(this, InAppProductActivity::class.java))
        }

        binding.btnMap.setOnClickListener {
            startActivity(Intent(this, AddressInfoActivity::class.java))
        }

        binding.btnConstruction.setOnClickListener {
            startActivity(Intent(this, ZippoConstructionActivity::class.java))
        }

        binding.btnList.setOnClickListener {
            startActivity(Intent(this, ZippoListActivity::class.java))
        }

        binding.btnHow.setOnClickListener {
            startActivity(Intent(this, ZippoHowToUseActivity::class.java))
        }
    }

    private fun getListData() = listOf(
        Zippo(
            src = R.drawable.zippo_1,
            name = "Compass Zippo",
            price = "0.5$",
            description = "Custom zippo",
            rank = 4.3f
        ),
        Zippo(
            src = R.drawable.zippo_2,
            name = "Building Zippo",
            price = "1$",
            description = "Custom zippo",
            rank = 4.3f
        ),
        Zippo(
            src = R.drawable.zippo_3,
            name = "Weapon Zippo",
            price = "3$",
            description = "Custom zippo",
            rank = 4.6f
        ),
        Zippo(
            src = R.drawable.zippo_4,
            name = "Hell Zippo",
            price = "5$",
            description = "Custom zippo",
            rank = 4.8f
        ),
        Zippo(
            src = R.drawable.zippo_5,
            name = "Art Zippo",
            price = "10$",
            description = "Custom zippo",
            rank = 4.9f
        ),
    )
}
