package com.social.uyirsocial.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.uyirsocial.*
import com.social.uyirsocial.domain.model.GalleryItem
import kotlinx.android.synthetic.main.fragment_gallery.*

class EventsFragment : Fragment() {
    private lateinit var galleryViewModel: EventsViewModel
    lateinit var galleryMainAdapter: EventsMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(EventsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        galleryViewModel.fetchGalleryItems()
        galleryMainAdapter = EventsMainAdapter{ id, title ->
            navigateToGalleryDetailsFragment(id,title)
        }
        gallery_main_recycler_view.apply {
            adapter = galleryMainAdapter
            layoutManager = LinearLayoutManager(this@EventsFragment.requireContext())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        galleryViewModel._galleryViewState.observe(viewLifecycleOwner, {
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

    private fun navigateToGalleryDetailsFragment(id: Int, title: String) {
        val action = EventsFragmentDirections.actionNavigationEventsToEventDetailsFragment().setTitle(title).setId(id)
        findNavController().navigate(action)
    }
}