package be.howest.marvindeckmyn

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.marvindeckmyn.beats.BeatsGridAdapter
import be.howest.marvindeckmyn.home.LatestBeatsGridAdapter
import be.howest.marvindeckmyn.home.NewestProducersGridAdapter
import be.howest.marvindeckmyn.network.Beats
import be.howest.marvindeckmyn.network.Producers
import be.howest.marvindeckmyn.network.SoldBeats
import be.howest.marvindeckmyn.producer.ProducerGridAdapter
import be.howest.marvindeckmyn.profile.ProfileGridAdapter
import be.howest.marvindeckmyn.soldbeats.SoldBeatsGridAdapter
import com.bumptech.glide.Glide

@BindingAdapter("listLatestBeatsData")
fun bindRecyclerViewLatestBeats(recyclerView: RecyclerView, data: List<Beats>?) {
    val adapter = recyclerView.adapter as LatestBeatsGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listNewestProducersData")
fun bindRecyclerViewNewestProducers(recyclerView: RecyclerView, data: List<Producers>?) {
    val adapter = recyclerView.adapter as NewestProducersGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listBeatsData")
fun bindRecyclerViewBeats(recyclerView: RecyclerView, data: List<Beats>?) {
    val adapter = recyclerView.adapter as BeatsGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listSoldBeatsData")
fun bindRecyclerViewSoldBeats(recyclerView: RecyclerView, data: List<SoldBeats>?) {
    val adapter = recyclerView.adapter as SoldBeatsGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listBeatsProfileData")
fun bindRecyclerViewBeatsProfile(recyclerView: RecyclerView, data: List<Beats>?) {
    val adapter = recyclerView.adapter as ProfileGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("listBeatsProducerData")
fun bindRecyclerViewBeatsProducer(recyclerView: RecyclerView, data: List<Beats>?) {
    val adapter = recyclerView.adapter as ProducerGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
    }
}