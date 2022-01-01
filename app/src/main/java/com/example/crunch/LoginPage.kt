package com.example.crunch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.crunch.databinding.FragmentLoginPageBinding

class LoginPage : Fragment() {

    lateinit var binding : FragmentLoginPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentLoginPageBinding.inflate(inflater)


        binding.logBtn.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.passText.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                findNavController().navigate(R.id.action_loginPage_to_foodFragment)
            } else{
                Toast.makeText(requireContext(), "Input Some Text", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }


}