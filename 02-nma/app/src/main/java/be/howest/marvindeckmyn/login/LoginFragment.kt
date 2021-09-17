package be.howest.marvindeckmyn.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.howest.marvindeckmyn.LoginActivity
import be.howest.marvindeckmyn.MainActivity
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.RegisterActivity
import be.howest.marvindeckmyn.database.MyUser
import be.howest.marvindeckmyn.database.UserDatabase
import be.howest.marvindeckmyn.databinding.FragmentLoginBinding
import be.howest.marvindeckmyn.network.User

class LoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false)

        val dataSource = context?.let { UserDatabase.getInstance(it).userDatabaseDao }
        val viewModelFactory = dataSource?.let { LoginViewModelFactory(it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(LoginViewModel::class.java)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val loginButton = binding.buttonLogin

        loginButton.setOnClickListener {
            val username = binding.loginUsernameForm.text.toString()
            val password = binding.loginPasswordForm.text.toString()

            val user = User(username, password)

            viewModel?.login(user)

            Handler().postDelayed({
                if (viewModel != null) {
                    if (viewModel.loginSuccessCheck == "true") {
                        viewModel.getProducerByUsername(username)
                        Handler().postDelayed({
                            val intent = Intent (activity, MainActivity::class.java)
                            activity?.startActivity(intent)

                        }, 1000)
                    } else {
                        Toast.makeText(context, "Login failed. Try again.", Toast.LENGTH_LONG).show()
                    }
                }
            }, 1000)
        }

        val registerButton = binding.buttonGoToRegister

        registerButton.setOnClickListener {
            val intent = Intent (activity, RegisterActivity::class.java)
            activity?.startActivity(intent)
        }

        return binding.root
    }
}