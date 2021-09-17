package be.howest.marvindeckmyn.settingspassword

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.*
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentSettingspasswordBinding
import be.howest.marvindeckmyn.network.NewPassword

class SettingsPasswordFragment : Fragment() {

    lateinit var settingsPasswordViewModel: SettingsPasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSettingspasswordBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_settingspassword, container, false)

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { SettingsPasswordViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(SettingsPasswordViewModel::class.java)
        }

        binding.lifecycleOwner = this

        if (viewModel != null) {
            settingsPasswordViewModel = viewModel
        }
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        val changeButton = binding.buttonChangePassword
        changeButton.setOnClickListener {
            val id = binding.idUser.text.toString().toInt()
            val oldpassword = binding.oldPasswordForm.text.toString()
            val newpassword = binding.newPasswordForm.text.toString()
            val confirmpassword = binding.confirmNewPasswordForm.text.toString()

            val newPassword = NewPassword(id, oldpassword, newpassword, confirmpassword)

            if (viewModel != null) {
                viewModel.changePassword(newPassword)
                Toast.makeText(context, "Password successfully changed!", Toast.LENGTH_LONG).show()
            }
        }

        val profileSettingsPage = binding.editProfile
        profileSettingsPage.setOnClickListener {
            val intent = Intent (activity, SettingsActivity::class.java)
            activity?.startActivity(intent)
        }

        val changeAvatarPage = binding.changeAvatar
        changeAvatarPage.setOnClickListener {
            val intent = Intent (activity, SettingsAvatarActivity::class.java)
            activity?.startActivity(intent)
        }

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
        settingsPasswordViewModel.logOut()
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