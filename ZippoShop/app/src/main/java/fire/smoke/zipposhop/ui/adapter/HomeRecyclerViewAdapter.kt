package fire.smoke.zipposhop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fire.smoke.zipposhop.databinding.ItemRvZippoBinding
import fire.smoke.zipposhop.models.Zippo

class HomeRecyclerViewAdapter :
    ListAdapter<Zippo, HomeRecyclerViewAdapter.ViewHolder>(RewardsDiffCallBack()) {

    var onItemClick: (Int) -> Unit = {}

    private class RewardsDiffCallBack : DiffUtil.ItemCallback<Zippo>() {
        override fun areItemsTheSame(oldItem: Zippo, newItem: Zippo): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Zippo, newItem: Zippo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvZippoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class ViewHolder(private val binding: ItemRvZippoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(zippo: Zippo) {
            binding.apply {
                zippo.src?.let { imgItem1.setImageResource(it) }
                itemView.setOnClickListener { onItemClick(zippo.src!!) }
                tvTitle1.text = zippo.name
                tvPrice1.text = zippo.price
                tvDescription1.text = zippo.description
                ratingBar1.rating = zippo.rank
            }
        }
    }
}
