package com.example.gelatocodingchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gelatocodingchallenge.databinding.FragmentGelleryBinding
import com.example.gelatocodingchallenge.models.GalleryModel
import com.example.gelatocodingchallenge.network.ApiService
import com.example.gelatocodingchallenge.ui.adapters.PhotoGridAdapter
import com.example.gelatocodingchallenge.ui.viewModels.GalleryViewModel
import com.example.gelatocodingchallenge.ui.viewModels.GalleryViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.gelatocodingchallenge.ui.activities.HomeActivity


class GalleryFragment : Fragment(),PhotoGridAdapter.OnItemClickListener {

    lateinit var galleryViewModel: GalleryViewModel
    lateinit var photoGridAdapter: PhotoGridAdapter
    private lateinit var binding: FragmentGelleryBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentGelleryBinding.inflate(inflater)
        setupViewModel()
        setupView()
        setupList()

        return binding.root
    }

    override fun onItemClick(photo: GalleryModel) {
        (activity as HomeActivity).showFragment(
            DetailFragment(),photo
        )
    }
    private fun setupViewModel() {
        val factory = GalleryViewModelFactory(ApiService())
        galleryViewModel = ViewModelProvider(this, factory).get(GalleryViewModel::class.java)
    }

    private fun setupView() {
        photoGridAdapter = PhotoGridAdapter(this)
        binding.photosGrid.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = photoGridAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupList() {
        lifecycleScope.launch {
            galleryViewModel.photos.collectLatest { pagedData ->
                photoGridAdapter.submitData(pagedData)
            }
        }
    }
}