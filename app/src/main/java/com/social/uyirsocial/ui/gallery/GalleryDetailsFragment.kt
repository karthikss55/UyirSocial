package com.social.uyirsocial.ui.gallery

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
import com.social.uyirsocial.visible

class GalleryDetailsFragment : Fragment() {
    private lateinit var binding: FragmentGalleryDetailsBinding
    private val args: GalleryDetailsFragmentArgs by navArgs()
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var galleryDetailAdapter: GalleryDetailAdapter
    private var itemId:Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        binding = FragmentGalleryDetailsBinding.inflate(inflater)
        itemId = args.id
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        galleryViewModel.fetchGalleryItems()
        galleryDetailAdapter = GalleryDetailAdapter()
        binding.galleryDetailRecyclerView.apply {
            adapter = galleryDetailAdapter
            layoutManager = LinearLayoutManager(this@GalleryDetailsFragment.requireContext())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        galleryViewModel._galleryViewState.observe(viewLifecycleOwner, {
            if (it.isLoading) {
                binding.progressView.visible()
            } else {
                binding.progressView.gone()
                updateAdapter(it.galleryItems)
            }
        })
    }

    private fun updateAdapter(galleryItems: List<GalleryItem>?) {
        galleryItems?.firstOrNull{
            it.id == itemId
        }?.apply {
            imageList?.let { galleryDetailAdapter.updateItems(it) }
        }
    }

}