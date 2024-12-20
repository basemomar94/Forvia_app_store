package com.bassem.forvia_app_store.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassem.forvia_app_store.databinding.ItemLargeAppBinding
import com.bassem.forvia_app_store.presentation.models.AppsUi
import com.bassem.forvia_app_store.presentation.utils.loadImage
import com.bumptech.glide.Glide

class EditorChoiceAdapter(
    private val items: List<AppsUi>,
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<EditorChoiceAdapter.LargeItemViewHolder>() {

    inner class LargeItemViewHolder(private val binding: ItemLargeAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(app: AppsUi) {
            binding.largeAppImage.loadImage(app.iconUrl)
            binding.largeAppName.text = app.name
            binding.root.setOnClickListener { onItemClick.onItemClick(app) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeItemViewHolder {
        val binding = ItemLargeAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LargeItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LargeItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}