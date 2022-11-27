package com.social.uyirsocial.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.social.uyirsocial.R
import com.social.uyirsocial.getDrawableByName

class EventDetailsAdapter: RecyclerView.Adapter<EventDetailsAdapter.GalleryDetailViewHolder>() {

    private val galleryImageItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_detail_image_item, parent, false)
        return GalleryDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryDetailViewHolder, position: Int) {
        val item = galleryImageItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = galleryImageItems.size

    fun updateItems(items: List<String>) {
        galleryImageItems.clear()
        galleryImageItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class GalleryDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val galleryImage: ImageView = itemView.findViewById(R.id.gallery_detail_image_view)
        fun bind(item: String) {
            galleryImage.setImageDrawable(itemView.context.getDrawableByName(item))
        }
    }
}