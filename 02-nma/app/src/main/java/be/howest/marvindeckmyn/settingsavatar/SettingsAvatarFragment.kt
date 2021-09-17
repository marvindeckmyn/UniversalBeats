package be.howest.marvindeckmyn.settingsavatar

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.*
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentSettingsavatarBinding
import be.howest.marvindeckmyn.network.Avatar
import java.io.ByteArrayOutputStream

class SettingsAvatarFragment : Fragment() {

    private lateinit var settingsAvatarViewModel: SettingsAvatarViewModel
    private lateinit var avatarUri: Uri
    private lateinit var avatar: ImageView

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingsavatarBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_settingsavatar, container, false
        )

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { SettingsAvatarViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(SettingsAvatarViewModel::class.java)
        }

        binding.lifecycleOwner = this

        if (viewModel != null) {
            settingsAvatarViewModel = viewModel
        }

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        val avatarUpload = binding.avatarUpload
        avatarUpload.setOnClickListener {
            pickImageGallery()
        }

        avatar = binding.avatarImage

        val changeButton = binding.avatarChange

        changeButton.setOnClickListener {
            val userId = binding.idUser.text.toString().toInt()

            val byteArrayOutputStream = ByteArrayOutputStream()

            val bitmapAvatar = MediaStore.Images.Media.getBitmap(context?.contentResolver, avatarUri)
            bitmapAvatar.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
            val avatarInByte = byteArrayOutputStream.toByteArray()
            val userAvatar = Base64.encodeToString(avatarInByte, Base64.DEFAULT)

            val avatarChange = Avatar(userId, userAvatar)


            viewModel?.changeAvatar(avatarChange)
            Toast.makeText(context, "Avatar successfully changed!", Toast.LENGTH_LONG).show()
        }

        val profileSettingsPage = binding.editProfile
        profileSettingsPage.setOnClickListener {
            val intent = Intent (activity, SettingsActivity::class.java)
            activity?.startActivity(intent)
        }

        val changePasswordPage = binding.changePassword
        changePasswordPage.setOnClickListener {
            val intent = Intent (activity, SettingsPasswordActivity::class.java)
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
        settingsAvatarViewModel.logOut()
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

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                avatarUri = data.data!!
            }
            avatar.setImageURI(data?.data)
        }
    }
}