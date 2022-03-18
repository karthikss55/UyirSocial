package com.social.uyirsocial.ui.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.social.uyirsocial.R
import com.social.uyirsocial.domain.model.ServicesViewItem
import com.social.uyirsocial.mapToSliderItems

class ServicesMainAdapter: RecyclerView.Adapter<ServicesMainAdapter.ServicesMainViewHolder>() {
    private val serviceItems = arrayListOf<ServicesViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesMainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.services_item, parent, false)
        val holder = ServicesMainViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ServicesMainViewHolder, position: Int) {
        val item = serviceItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = serviceItems.size

    fun updateItems(items: List<ServicesViewItem>) {
        serviceItems.clear()
        serviceItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class ServicesMainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.service_title_txt)
        private val contentText: TextView = itemView.findViewById(R.id.service_content_txt)
        private val sliderView: ImageSlider = itemView.findViewById(R.id.service_image_slider)
        fun bind(item: ServicesViewItem) {
            titleText.text = item.title
            contentText.text = item.title
            item.imageList?.mapToSliderItems(itemView.context)?.let {
                sliderView.setImageList(it)
            }
        }
    }
}