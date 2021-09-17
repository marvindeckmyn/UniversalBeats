package be.howest.marvindeckmyn.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.marvindeckmyn.databinding.GridViewLatestBeatsBinding
import be.howest.marvindeckmyn.network.Beats

class LatestBeatsGridAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<Beats,
                LatestBeatsGridAdapter.BeatsViewHolder>(DiffCallBack) {

    class BeatsViewHolder(private var binding: GridViewLatestBeatsBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(beats: Beats) {
            binding.beats = beats
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Beats>() {
        override fun areItemsTheSame(oldItem: Beats, newItem: Beats): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Beats, newItem: Beats): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BeatsViewHolder {
        return BeatsViewHolder(GridViewLatestBeatsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BeatsViewHolder, position: Int) {
        val beats = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(beats)
        }
        holder.bind(beats)
    }

    class OnClickListener(val clickListener: (beats: Beats) -> Unit) {
        fun onClick(beats: Beats) = clickListener(beats)
    }
}