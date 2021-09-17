package be.howest.marvindeckmyn.soldbeats

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.*
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentSoldbeatsBinding

class SoldBeatsFragment : Fragment() {

    lateinit var soldBeatsViewModel: SoldBeatsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSoldbeatsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_soldbeats, container, false
        )

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { SoldBeatsViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(SoldBeatsViewModel::class.java)
        }

        binding.lifecycleOwner = this

        if (viewModel != null) {
            soldBeatsViewModel = viewModel
        }

        binding.viewModel = viewModel

        binding.soldBeatsGrid.adapter = SoldBeatsGridAdapter(SoldBeatsGridAdapter.OnClickListener {
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun goToHome() {
        setHasOptionsMenu(false)
        val intent = Intent (activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun goToSoldBeats() {
        val intent = Intent (activity, SoldBeatsActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun goToSettings() {
        val intent = Intent (activity, SettingsActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun logOut() {
        setHasOptionsMenu(false)
        soldBeatsViewModel.logOut()
        val intent = Intent (activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.homepage -> goToHome()
            R.id.sold_beats -> goToSoldBeats()
            R.id.settings -> goToSettings()
            R.id.logout -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }
}