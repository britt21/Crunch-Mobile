package com.example.crunch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crunch.databinding.FoodItemsBinding
import com.example.crunch.foodlist.FoodList
import com.example.crunch.foodlist.Result

class FoodAdapter() : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder (private val binding: FoodItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
        }
    }
    class OnClickListiner(val clickListiner : (result : Result) -> Unit){
        fun onClick(result: Result) = clickListiner(result)

    }

  private  var result = emptyList<Result>()

    var onClick : ((Result) ->Unit)? = null

    fun onMovieClick(listiner : (Result) -> Unit){
        onClick = listiner
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(FoodItemsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = result[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onClick?.let {
                it(currentItem)
            }
        }

    }

    override fun getItemCount(): Int {
       return result.size
    }

    fun setData(newData: FoodList){
        val DiffCallback = DiffCallback(result, newData.results)
        val DiffResult = DiffUtil.calculateDiff(DiffCallback)

        result = newData.results
        DiffResult.dispatchUpdatesTo(this)
    }
}




class DiffCallback(
    private val oldList : List<Result>,
    private val newList: List<Result>)
    : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size

    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] == newList[newItemPosition]
    }




}