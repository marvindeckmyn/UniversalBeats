package be.howest.marvindeckmyn.soldbeats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.marvindeckmyn.databinding.GridViewSoldBeatsBinding
import be.howest.marvindeckmyn.network.SoldBeats

class SoldBeatsGridAdapter(private val onClickListener: OnClickListener) :
        ListAdapter<SoldBeats,
                SoldBeatsGridAdapter.SoldBeatsViewHolder>(DiffCallBack){

    class SoldBeatsViewHolder(private var binding: GridViewSoldBeatsBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(soldBeats: SoldBeats) {
                    binding.soldBeats = soldBeats
                    binding.executePendingBindings()
                }
            }

    companion object DiffCallBack : DiffUtil.ItemCallback<SoldBeats>() {
        override fun areItemsTheSame(oldItem: SoldBeats, newItem: SoldBeats): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SoldBeats, newItem: SoldBeats): Boolean {
            return oldItem.data == newItem.data
        }
    }

    class OnClickListener(val clickListener: (soldBeats: SoldBeats) -> Unit) {
        fun onClick(soldBeats: SoldBeats) = clickListener(soldBeats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldBeatsViewHolder {
        return SoldBeatsViewHolder(GridViewSoldBeatsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SoldBeatsViewHolder, position: Int) {
        val soldBeats = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(soldBeats)
        }
        holder.bind(soldBeats)
    }

}