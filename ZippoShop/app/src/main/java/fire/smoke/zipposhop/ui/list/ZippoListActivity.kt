package fire.smoke.zipposhop.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fire.smoke.zipposhop.database.AppDatabase
import fire.smoke.zipposhop.databinding.ActivityListBinding
import fire.smoke.zipposhop.models.Zippo
import fire.smoke.zipposhop.ui.adapter.HomeRecyclerViewAdapter

class ZippoListActivity : AppCompatActivity() {

    private var _binding: ActivityListBinding? = null
    private val binding get() = _binding!!
    private val adapter: HomeRecyclerViewAdapter by lazy { HomeRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = AppDatabase.newInstance(this)?.zippos?.shuffled()
        initBindingView(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initBindingView(list: List<Zippo?>?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.submitList(list)
    }
}