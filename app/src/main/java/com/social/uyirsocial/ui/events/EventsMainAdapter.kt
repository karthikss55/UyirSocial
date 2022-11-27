package com.social.uyirsocial.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.social.uyirsocial.R
import com.social.uyirsocial.domain.model.GalleryItem
import com.social.uyirsocial.mapToSliderItems

class EventsMainAdapter(val click:(Int, String) -> Unit) : RecyclerView.Adapter<EventsMainAdapter.GalleryMainViewHolder>() {
    private val galleryItems = arrayListOf<GalleryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryMainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.gallery_main_item, parent, false)
        return GalleryMainViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryMainViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = galleryItems.size

    fun updateItems(items: List<GalleryItem>) {
        galleryItems.clear()
        galleryItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class GalleryMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.gallery_title_txt)
        private val placeText: TextView = itemView.findViewById(R.id.place_txt)
        private val dateText: TextView = itemView.findViewById(R.id.date_text)
        private val sliderView: ImageSlider = itemView.findViewById(R.id.image_slider)
        private val moreText: TextView = itemView.findViewById(R.id.more_txt)
        private val imageContainer = itemView.findViewById<ViewGroup>(R.id.image_container)

        fun bind(item: GalleryItem) {
            titleText.text = item.title
            item.imageList?.mapToSliderItems(itemView.context)?.let {
                sliderView.setImageList(it)
            }
            placeText.text = item.place
            dateText.text = item.date
            moreText.setOnClickListener {
                click(item.id, item.title.orEmpty())
            }
            itemView.setOnClickListener {
                click(item.id, item.title.orEmpty())
            }
            imageContainer.setOnClickListener {
                click(item.id, item.title.orEmpty())
            }
        }
    }

}