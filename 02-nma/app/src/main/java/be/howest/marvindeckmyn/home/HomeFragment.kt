package be.howest.marvindeckmyn.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.howest.marvindeckmyn.beats.BeatsFragmentDirections
import be.howest.marvindeckmyn.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.latestBeatsGrid.adapter = LatestBeatsGridAdapter(LatestBeatsGridAdapter.OnClickListener {
            viewModel.displayBeat(it)
        })

        viewModel.navigateToSelectedBeat.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(HomeFragmentDirections.actionShowBeat(it))
                viewModel.displayBeatComplete()
            }
        })

        binding.newestProducersGrid.adapter = NewestProducersGridAdapter(NewestProducersGridAdapter.OnClickListener {
            viewModel.displayProducer(it)
        })

        viewModel.navigateToSelectedProducer.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(HomeFragmentDirections.actionShowProducer(it))
                viewModel.displayProducerComplete()
            }
        })

        return binding.root
    }
}