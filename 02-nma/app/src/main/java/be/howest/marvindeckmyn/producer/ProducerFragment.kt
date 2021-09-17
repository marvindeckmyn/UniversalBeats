package be.howest.marvindeckmyn.producer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentProducerBinding

class ProducerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding: FragmentProducerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_producer, container, false
        )

        val producer = ProducerFragmentArgs.fromBundle(requireArguments()).selectedProducer
        val viewModelFactory = ProducerViewModelFactory(producer, application)
        val viewModel = viewModelFactory.let {
            ViewModelProvider(
                this,
                it
            ).get(ProducerViewModel::class.java)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.beatsProducerGrid.adapter =
            ProducerGridAdapter(ProducerGridAdapter.OnClickListener {
                viewModel.displayBeat(it)
            })

        viewModel.navigateToSelectedBeat.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(ProducerFragmentDirections.actionShowBeat(it))
                viewModel.displayBeatComplete()
            }
        })

        val youtubeProfile = viewModel.selectedProducer.value?.youtube
        val instagramProfile = viewModel.selectedProducer.value?.instagram
        val twitterProfile = viewModel.selectedProducer.value?.twitter

        if (youtubeProfile == null) {
            binding.producerYoutube.text = ""
        } else {
            binding.producerYoutube.text =
                Html.fromHtml("<a href=\"https://www.youtube.com/${youtubeProfile}\">YouTube</a>")
            binding.producerYoutube.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/${youtubeProfile}")
                )
                startActivity(browserIntent)
            }
        }

        if (instagramProfile == null) {
            binding.producerInstagram.text = ""
        } else {
            binding.producerInstagram.text =
                Html.fromHtml("<a href=\"https://www.instagram.com/${instagramProfile}\">Instagram</a>")
            binding.producerInstagram.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/${instagramProfile}")
                )
                startActivity(browserIntent)
            }
        }

        if (twitterProfile == null) {
            binding.producerTwitter.text = ""
        } else {
            binding.producerTwitter.text =
                Html.fromHtml("<a href=\"https://twitter.com/${twitterProfile}\">Twitter</a>")
            binding.producerTwitter.setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/${twitterProfile}"))
                startActivity(browserIntent)
            }
        }

        return binding.root
    }
}