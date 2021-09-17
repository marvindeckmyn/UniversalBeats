package be.howest.marvindeckmyn.beats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.databinding.FragmentBeatsBinding

class BeatsFragment : Fragment() {

    private val viewModel: BeatsViewModel by lazy {
        ViewModelProvider(this).get(BeatsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentBeatsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.beatsGrid.adapter = BeatsGridAdapter(BeatsGridAdapter.OnClickListener {
            viewModel.displayBeat(it)
        })

        viewModel.navigateToSelectedBeat.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(BeatsFragmentDirections.actionShowBeat(it))
                viewModel.displayBeatComplete()
            }
        })

        return binding.root
    }
}