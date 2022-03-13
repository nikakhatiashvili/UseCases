package com.example.usecases.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usecases.databinding.CryptoItemBinding
import com.example.usecases.model.CryptoItem
import com.example.usecases.util.dollarString
import com.example.usecases.util.smalldollarString

class MainAdapter(): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var data: List<CryptoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(
            CryptoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind()
    }

    override fun getItemCount()= data.size

    inner class ViewHolder(private val binding: CryptoItemBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var currentData:CryptoItem
        @SuppressLint("ResourceAsColor")
        fun bind(){
            currentData = data[adapterPosition]
            Glide.with(itemView.context).load(currentData.image).into(binding.coinsItemImageView)

            binding.coinsItemSymbolTextView.text = currentData.symbol
            if(currentData.current_price.toString().first().toString() == "0"){
                binding.coinsItemPriceTextView.text = currentData.current_price.smalldollarString()
            }else{
                binding.coinsItemPriceTextView.text = currentData.current_price.dollarString()
            }
            binding.coinsItemNameTextView.text = currentData.name
        }
    }
}