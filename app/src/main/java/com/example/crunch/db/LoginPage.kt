package com.example.crunch.db

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.crunch.R
import com.example.crunch.databinding.FragmentLoginPageBinding
import com.example.crunch.model.LoginViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

const val SIGN_IN_REQUEST_CODE = 10001

class LoginPage : Fragment() {

    val TAG = "MainFragment"

private val viewModel by viewModels<LoginViewModel>()
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

            }else{
                Toast.makeText(requireContext(), "Insert Some Text in the text box", Toast.LENGTH_SHORT)
            }


        }


        return binding.root
    }

    private fun launchSignInFlow() {
        val provider = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(provider).build()
            , SIGN_IN_REQUEST_CODE
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(requireContext(), "Sucessfuly signed in User ${FirebaseAuth
                    .getInstance().currentUser?.displayName
                }", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_loginPage_to_foodFragment)
            }else{
                Toast.makeText(requireContext(), "Failed ${response?.error?.errorCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}