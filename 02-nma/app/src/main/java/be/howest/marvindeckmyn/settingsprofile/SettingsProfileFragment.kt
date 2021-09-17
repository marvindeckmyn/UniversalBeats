package be.howest.marvindeckmyn.settingsprofile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.*
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentSettingsprofileBinding
import be.howest.marvindeckmyn.network.UserInfo

class SettingsProfileFragment : Fragment() {

    lateinit var settingsProfileViewModel: SettingsProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSettingsprofileBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_settingsprofile, container, false)

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { SettingsProfileViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(SettingsProfileViewModel::class.java)
        }

        binding.lifecycleOwner = this

        if (viewModel != null) {
            settingsProfileViewModel = viewModel
        }
        binding.viewModel = viewModel

        val genreValues = arrayListOf<String>()

        Handler().postDelayed({
            if (viewModel != null) {
                for (genre in viewModel.genres.value!!)
                    genreValues.add(genre.name)

                val genreSpinnerAdapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, genreValues) }
                binding.editGenreForm.adapter = genreSpinnerAdapter

                selectGenre(viewModel, binding)
            }
        }, 1000)

        setHasOptionsMenu(true)

        val updateButton = binding.buttonSave
        updateButton.setOnClickListener {
            val id = binding.idUser.text.toString().toInt()
            val username = binding.editUsernameForm.text.toString()
            val name = binding.editNameForm.text.toString()
            val email = binding.editEmailForm.text.toString()
            val aboutme = binding.editAboutMeForm.text.toString()
            val genre = binding.editGenreForm.selectedItem.toString()
            val twitter = binding.editTwitterForm.text.toString()
            val instagram = binding.editInstagramForm.text.toString()
            val youtube = binding.editYoutubeForm.text.toString()

            val userInfo = UserInfo(id, username, name, email, aboutme, genre, twitter, instagram, youtube)

            if (viewModel != null) {
                viewModel.updateUserInfo(userInfo)
                Toast.makeText(context, "Info successfully updated!", Toast.LENGTH_SHORT).show()
            }
        }

        val avatarSettingsPage = binding.changeAvatar
        avatarSettingsPage.setOnClickListener {
            val intent = Intent (activity, SettingsAvatarActivity::class.java)
            activity?.startActivity(intent)
        }

        val changePasswordPage = binding.changePassword
        changePasswordPage.setOnClickListener {
            val intent = Intent (activity, SettingsPasswordActivity::class.java)
            activity?.startActivity(intent)
        }

        return binding.root
    }

    private fun selectGenre(
        viewModel: SettingsProfileViewModel,
        binding: FragmentSettingsprofileBinding
    ) {
        if (viewModel.producer.value?.get(0)?.genre == "Hip Hop") {
            binding.editGenreForm.setSelection(0)
        } else if (viewModel.producer.value?.get(0)?.genre == "Pop") {
            binding.editGenreForm.setSelection(1)
        } else if (viewModel.producer.value?.get(0)?.genre == "R&B") {
            binding.editGenreForm.setSelection(2)
        } else if (viewModel.producer.value?.get(0)?.genre == "Rock") {
            binding.editGenreForm.setSelection(3)
        } else if (viewModel.producer.value?.get(0)?.genre == "Electronic") {
            binding.editGenreForm.setSelection(4)
        } else if (viewModel.producer.value?.get(0)?.genre == "Reggae") {
            binding.editGenreForm.setSelection(5)
        } else if (viewModel.producer.value?.get(0)?.genre == "Country") {
            binding.editGenreForm.setSelection(6)
        } else if (viewModel.producer.value?.get(0)?.genre == "World") {
            binding.editGenreForm.setSelection(7)
        }
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
        settingsProfileViewModel.logOut()
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