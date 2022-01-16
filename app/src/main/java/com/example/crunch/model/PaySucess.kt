package com.example.crunch.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.crunch.R
import com.example.crunch.databinding.FragmentPaySucessBinding


class PaySucess : Fragment() {

    lateinit var binding : FragmentPaySucessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_paySucess_to_foodFragment)
        }
    binding = FragmentPaySucessBinding.inflate(inflater)


        return binding.root
    }

}