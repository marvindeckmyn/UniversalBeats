package be.howest.marvindeckmyn.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import be.howest.marvindeckmyn.R
import be.howest.marvindeckmyn.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cart, container, false)

        return binding.root
    }
}