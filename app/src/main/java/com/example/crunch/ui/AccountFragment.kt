package com.example.crunch.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.crunch.network.PushNotification
import com.example.crunch.R
import com.example.crunch.network.RetrofitInstance
import com.example.crunch.databinding.FragmentAccountBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

const val TAG ="DetailFragment"
const val TOPIC = "/topics/myTopic"
class AccountFragment : Fragment() {


    lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        val intent = Intent()
        intent.type = String()
        intent.action = Intent.ACTION_GET_CONTENT

        binding.PayIt.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_orderList)

        }

        return binding.root
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == 0 ){
//            val uri =data?.data
//            binding.ivimage.setImageURI(uri)
//        }
//    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {

        try {
            val response = RetrofitInstance.api.postNtification(notification)
            if (response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }else{
                Log.e(TAG, response.errorBody().toString())
            }

        }catch (e: Exception){
            Log.e(TAG, e.toString())
        }

    }




}