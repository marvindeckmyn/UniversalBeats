package be.howest.marvindeckmyn.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.howest.marvindeckmyn.*
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentProfileBinding
import be.howest.marvindeckmyn.network.SoldBeats

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { ProfileViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(ProfileViewModel::class.java)
        }

        binding.lifecycleOwner = this

        if (viewModel != null) {
            profileViewModel = viewModel
        }
        binding.viewModel = viewModel

        binding.beatsProducerGrid.adapter = ProfileGridAdapter(ProfileGridAdapter.OnClickListener {
            viewModel?.displayBeat(it)
        })

        viewModel?.navigateToSelectedBeat?.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(ProfileFragmentDirections.actionShowBeat(it))
                viewModel.displayBeatComplete()
            }
        })


        val youtubeProfile = viewModel?.producer?.value?.get(0)?.youtube
        val instagramProfile = viewModel?.producer?.value?.get(0)?.instagram
        val twitterProfile = viewModel?.producer?.value?.get(0)?.twitter

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

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun goToHome() {
        setHasOptionsMenu(false)
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun goToSoldBeats() {
        val intent = Intent(activity, SoldBeatsActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun goToSettings() {
        val intent = Intent(activity, SettingsActivity::class.java)
        activity?.startActivity(intent)
    }

    private fun logOut() {
        setHasOptionsMenu(false)
        profileViewModel.logOut()
        val intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homepage -> goToHome()
            R.id.sold_beats -> goToSoldBeats()
            R.id.settings -> goToSettings()
            R.id.logout -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }

}