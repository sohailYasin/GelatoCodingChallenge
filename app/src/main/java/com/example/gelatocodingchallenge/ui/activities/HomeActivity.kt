package com.example.gelatocodingchallenge.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gelatocodingchallenge.databinding.ActivityHomeBinding
import com.example.gelatocodingchallenge.models.GalleryModel


class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showFragment(fragment: Fragment, photo: GalleryModel) {

        val bundle = Bundle()
        bundle.putString("ImageURL", photo.download_url)
        fragment.setArguments(bundle)
        supportFragmentManager.beginTransaction()
            .add(binding.overviewFragment.id, fragment)
            .addToBackStack(null)
            .commit()
    }


}