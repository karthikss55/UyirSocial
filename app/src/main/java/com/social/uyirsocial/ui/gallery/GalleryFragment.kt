package com.social.uyirsocial.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.uyirsocial.*
import com.social.uyirsocial.domain.model.GalleryItem
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    private lateinit var dashboardViewModel: GalleryViewModel
    lateinit var galleryMainAdapter: GalleryMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        dashboardViewModel.fetchGalleryItems()
        galleryMainAdapter = GalleryMainAdapter()
        gallery_main_recycler_view.apply {
            adapter = galleryMainAdapter
            layoutManager = LinearLayoutManager(this@GalleryFragment.requireContext())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        dashboardViewModel._galleryViewState.observe(viewLifecycleOwner, {
            if (it.isLoading) {
                progress_view.visible()
            } else {
                progress_view.gone()
                updateAdapter(it.galleryItems)
            }
        })
    }

    private fun updateAdapter(galleryItems: List<GalleryItem>?) {
        galleryItems?.let {
            galleryMainAdapter.updateItems(it)
        }
    }
}