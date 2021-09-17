package be.howest.marvindeckmyn.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.marvindeckmyn.databinding.GridViewNewestProducersBinding
import be.howest.marvindeckmyn.network.Producers

class NewestProducersGridAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<Producers,
                NewestProducersGridAdapter.ProducersViewHolder>(DiffCallBack) {

    class ProducersViewHolder(private var binding: GridViewNewestProducersBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(producers: Producers) {
            binding.producers = producers
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Producers>() {
        override fun areItemsTheSame(oldItem: Producers, newItem: Producers): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Producers, newItem: Producers): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducersViewHolder {
        return ProducersViewHolder(GridViewNewestProducersBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProducersViewHolder, position: Int) {
        val producers = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(producers)
        }
        holder.bind(producers)
    }

    class OnClickListener(val clickListener: (producers: Producers) -> Unit) {
        fun onClick(producers: Producers) = clickListener(producers)
    }
}