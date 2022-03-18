package com.social.uyirsocial.ui.services

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.social.uyirsocial.R
import com.social.uyirsocial.domain.model.ServicesViewItem
import com.social.uyirsocial.gone
import com.social.uyirsocial.visible
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_services.*

class ServicesFragment : Fragment() {

    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var servicesAdapter: ServicesMainAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        servicesViewModel =
            ViewModelProvider(this).get(ServicesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        servicesViewModel.fetchOurServicesData()
        servicesAdapter = ServicesMainAdapter()
        services_recycler_view.apply {
            adapter = servicesAdapter
            layoutManager = LinearLayoutManager(this@ServicesFragment.requireContext())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        servicesViewModel._servicesViewState.observe(viewLifecycleOwner, {
            if (it.isLoading) {
                services_progress_view.visible()
            } else {
                services_progress_view.gone()
                updateAdapter(it.items)
            }
        })
    }

    private fun updateAdapter(galleryItems: List<ServicesViewItem>?) {
        galleryItems?.let {
            servicesAdapter.updateItems(it)
        }
    }
}