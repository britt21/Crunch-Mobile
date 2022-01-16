package com.example.crunch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.crunch.model.DetailViewModel
import com.example.crunch.db.DetailViewModelFactory
import com.example.crunch.network.NotificationData
import com.example.crunch.network.PushNotification
import com.example.crunch.databinding.FragmentDetailsBinding
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*


//const val TOPIC = "/topics/myTopic"

@AndroidEntryPoint
class DetailsFragment : Fragment() {
private val args by navArgs<com.example.crunch.DetailsFragmentArgs>()

    lateinit var detailViewModel: DetailViewModel
    lateinit var binding : FragmentDetailsBinding
    lateinit var  numb : TextView





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        // Inflate the layout for this fragment
binding = FragmentDetailsBinding.inflate(inflater)
        args.selectedProperty

        val application = requireNotNull(activity).application
        val food = com.example.crunch.DetailsFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = DetailViewModelFactory(food, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


             numb =  binding.qtyNumb

    binding.incrBtn.setOnClickListener {
       updateQty()

            }
binding.decrBtn.setOnClickListener {
    updateDecQty()
}


        binding.orderFood.setOnClickListener {
            Toast.makeText(requireContext(), "Your Purchase was Successful", Toast.LENGTH_LONG).show()
            val title = binding.etTitle.text.toString()
            val  body = binding.etSummary.text.toString()
            val dataTitle : String = "New Order Placed"
            val dataBody : String = "OrderID: 92826261083"
                PushNotification(
                    NotificationData(dataTitle, dataBody),
                    TOPIC
                ).also {
                    detailViewModel.sendNotification(it)


                }


            }





return binding.root

    }

    private fun updateDecQty() {
        detailViewModel.DecTrigger()
        binding.qtyNumb.text = detailViewModel.ProductQty.toString()
        binding.dollarNumb.text = detailViewModel.ProductQty.toString()


    }

    private fun updateQty() {
        detailViewModel.incrTriger()
        binding.qtyNumb.text = detailViewModel.ProductQty.toString()
        binding.dollarNumb.text = detailViewModel.ProductQty.toString()

    }



}