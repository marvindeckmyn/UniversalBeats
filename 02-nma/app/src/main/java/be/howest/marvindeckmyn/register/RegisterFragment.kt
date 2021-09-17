package be.howest.marvindeckmyn.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.LoginActivity
import be.howest.marvindeckmyn.MainActivity
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentRegisterBinding
import be.howest.marvindeckmyn.network.RegisterUser

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_register, container, false)

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { RegisterViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(RegisterViewModel::class.java)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val genreValues = arrayListOf<String>()

        Handler().postDelayed({
            if (viewModel != null) {
                for (genre in viewModel.genres.value!!)
                    genreValues.add(genre.name)

                val genreSpinnerAdapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, genreValues) }
                binding.registerGenreForm.adapter = genreSpinnerAdapter
            }
        }, 1000)

        val registerButton = binding.buttonRegister

        registerButton.setOnClickListener {
            val countUsers = viewModel?.producers?.value?.size

            val userId = countUsers?.plus(1)
            val username = binding.registerUsernameForm.text.toString()
            val name = binding.registerFullnameForm.text.toString()
            val email = binding.registerEmailForm.text.toString()
            val password = binding.registerPasswordForm.text.toString()
            val confirmPassword = binding.registerConfirmPasswordForm.text.toString()
            val aboutme = binding.registerAboutMeForm.text.toString()
            val genre = binding.registerGenreForm.selectedItem.toString()
            val twitter = binding.registerTwitterForm.text.toString()
            val instagram = binding.registerInstagramForm.text.toString()
            val youtube = binding.registerYoutubeForm.text.toString()

            val userToRegister = userId?.let { it1 -> RegisterUser(it1, username, name, email, password, confirmPassword, aboutme, genre, twitter, instagram, youtube) }

            if (userToRegister != null) {
                viewModel.register(userToRegister)

                Handler().postDelayed({
                    if (viewModel.registerSuccessCheck == "true") {
                        viewModel.getProducerByUsername(username)
                        Handler().postDelayed({
                            val intent = Intent (activity, MainActivity::class.java)
                            activity?.startActivity(intent)
                        }, 1000)
                    } else {
                        Toast.makeText(context, "Register failed. Try again.", Toast.LENGTH_LONG).show()
                    }
                }, 1000
                )
            }
        }

        val loginButton = binding.buttonGoToLogin

        loginButton.setOnClickListener {
            val intent = Intent (activity, LoginActivity::class.java)
            activity?.startActivity(intent)
        }

        return binding.root
    }
}