package com.example.gelatocodingchallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import coil.request.CachePolicy
import com.example.gelatocodingchallenge.R
import com.example.gelatocodingchallenge.databinding.FragmentDetailBinding
import android.graphics.Bitmap

import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


private lateinit var binding: FragmentDetailBinding

class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        val imageURL = this.arguments!!.getString("ImageURL")
        setImage(imageURL)
        setClickListeners(imageURL)


        return binding.root
    }

    private fun setClickListeners(ImageURL: String?) {
        binding.socialShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, ImageURL)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.saveLocally.setOnClickListener {
            saveImageLocally()
        }
    }

    private fun setImage(ImageURL: String?) {
        binding.detailImage.load(ImageURL) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
            diskCachePolicy(CachePolicy.ENABLED)
        }
    }

    private fun saveImageLocally() {

    }

}