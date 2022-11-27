package com.social.uyirsocial.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.uyirsocial.databinding.FragmentGalleryDetailsBinding
import com.social.uyirsocial.domain.model.GalleryItem
import com.social.uyirsocial.gone
import com.social.uyirsocial.mapToSliderItems
import com.social.uyirsocial.visible

class EventDetailsFragment : Fragment() {
    private lateinit var binding: FragmentGalleryDetailsBinding
    private val args: EventDetailsFragmentArgs by navArgs()
    private lateinit var galleryViewModel: EventsViewModel
    private lateinit var galleryDetailAdapter: EventDetailsAdapter
    private var itemId:Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewModel =
            ViewModelProvider(this).get(EventsViewModel::class.java)
        binding = FragmentGalleryDetailsBinding.inflate(inflater)
        itemId = args.id
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        galleryViewModel.fetchGalleryItems()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        galleryViewModel._galleryViewState.observe(viewLifecycleOwner, {
            if (it.isLoading) {
                binding.progressView.visible()
            } else {
                binding.progressView.gone()
                updateImageSlider(it.galleryItems)
            }
        })
    }

    private fun updateImageSlider(galleryItems: List<GalleryItem>?) {
        galleryItems?.firstOrNull{
            it.id == itemId
        }?.apply {
            context?.let {
                imageList?.mapToSliderItems(it)?.let {
                    binding.imageSlider.setImageList(it)
                }
            }
        }
    }

}