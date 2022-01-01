package com.example.crunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.crunch.databinding.FragmentSplashBinding

class Splash : Fragment() {

    lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentSplashBinding.inflate(inflater)


        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_splash_to_loginPage)
        }

        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_splash_to_loginPage)
        }

        return binding.root
    }

}