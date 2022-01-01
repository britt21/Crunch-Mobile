package com.example.crunch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.slider_item.view.*

class SlideAdapter (val  imgList : List<Int>) : RecyclerView.Adapter<SlideAdapter.SliderViewHolder>()
{

    inner  class SliderViewHolder(var view : View) :  RecyclerView.ViewHolder(view){

        val img = view.findViewById<ImageView>(R.id.image_slide)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.slider_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listImg = imgList[position]
        holder.itemView.image_slide.setImageResource(listImg)
//        if (position == imgList.size -2 ){
//            viewPager.post(run)
//        }
    }

//   private val run = Runnable {
//       imgList.addAll(imgList)
//       notifyDataSetChanged()
//   }

    override fun getItemCount(): Int = imgList.size
}