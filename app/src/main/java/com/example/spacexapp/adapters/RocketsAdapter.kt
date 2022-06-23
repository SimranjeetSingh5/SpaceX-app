package com.example.spacexapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.spacexapp.databinding.ItemRocketBinding
import com.example.spacexapp.listeners.RocketListeners
import com.example.spacexapp.models.Rocket
import eightbitlab.com.blurview.RenderScriptBlur

class RocketsAdapter(private var rocketListeners :RocketListeners): RecyclerView.Adapter<ViewHolder>() {



    private val differCallback by lazy {
        object : DiffUtil.ItemCallback<Rocket>(){
            override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
                return oldItem == newItem
            }

        }
    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRocketBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val rocket =  differ.currentList[position]
        val radius = 5f
//        holder.binding.blurView.setupWith(holder.binding.root)
//            .setBlurRadius(radius)
//            .setBlurEnabled(true)
//            .setBlurAlgorithm(RenderScriptBlur())
//            .setBlurRadius(radius)
//            .setBlurAutoUpdate(true)
        holder.binding.name.text = rocket.name
        holder.binding.country.text = rocket.country
        holder.binding.engines.text = rocket.engines?.number.toString()
        val myOptions = RequestOptions()
            .centerCrop()
            .override(100, 100)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(holder.itemView.context)
            .load(rocket.flickrImages[0])
            .apply(myOptions)
            .placeholder(R.drawable.stars)
            .into(holder.binding.ivImage)
        holder.binding.root.setOnClickListener { rocketListeners.onItemClicked(rocket) }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
class ViewHolder(val binding: ItemRocketBinding):RecyclerView.ViewHolder(binding.root)
