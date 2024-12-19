package com.bassem.forvia_app_store.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassem.forvia_app_store.data.models.AppDetails
import com.bassem.forvia_app_store.databinding.ItemSmallAppBinding
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bumptech.glide.Glide

class SmallItemsAdapter(
    private val items: List<AppsUi>,
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<SmallItemsAdapter.SmallItemViewHolder>() {

    inner class SmallItemViewHolder(private val binding: ItemSmallAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(app: AppsUi) {
            Glide.with(binding.appThumbnail.context).load(app.iconUrl).into(binding.appThumbnail)
            binding.appNameText.text = app.name
            binding.appRatingBar.rating = app.rating.toFloat()

            binding.root.setOnClickListener { onItemClick.onItemClick(app) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallItemViewHolder {
        val binding =
            ItemSmallAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SmallItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SmallItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}