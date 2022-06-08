package com.example.gelatocodingchallenge.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.gelatocodingchallenge.R
import com.example.gelatocodingchallenge.databinding.GridViewItemBinding
import com.example.gelatocodingchallenge.models.GalleryModel


class PhotoGridAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GalleryModel, PhotoGridAdapter.GalleryViewHolder>(GalleryComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryViewHolder {
        return GalleryViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class GalleryViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }

            }
        }

        fun bindPassenger(item: GalleryModel) = with(binding) {
            Log.d("imageIssue", "bindPassenger: " + item.download_url)
            picsumImage.load(item.download_url) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
                diskCachePolicy(CachePolicy.ENABLED)
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: GalleryModel)
    }

    object GalleryComparator : DiffUtil.ItemCallback<GalleryModel>() {
        override fun areItemsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem == newItem
        }
    }
}