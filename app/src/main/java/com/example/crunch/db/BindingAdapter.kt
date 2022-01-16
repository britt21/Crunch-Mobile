package com.example.crunch.db

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.crunch.foodlist.FoodList
import org.jsoup.Jsoup

@BindingAdapter("imgUrl")
fun bindimg(imageView: ImageView,ImgUrl : String){
    imageView.load(ImgUrl){
        crossfade(600)
    }
}


@BindingAdapter("listitems")
fun bindrecycle(recyclerView: RecyclerView, data: FoodList){
    val adapter = recyclerView.adapter as FoodAdapter
    adapter.setData(data)
}


@BindingAdapter("parced")
fun parceHtml(textView: TextView, description : String?){
    if (description != null){
        val desc = Jsoup.parse(description).text()
        textView.text = desc
    }
}

