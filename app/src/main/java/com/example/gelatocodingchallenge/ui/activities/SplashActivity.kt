package com.example.gelatocodingchallenge.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gelatocodingchallenge.R
import com.example.gelatocodingchallenge.models.SplashModel
import com.example.gelatocodingchallenge.ui.viewModels.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        initViewModel()
        observeSplashLiveData()
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
    }

    private fun observeSplashLiveData() {
        splashViewModel.initSplashScreen()
        val observer = Observer<SplashModel> {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        splashViewModel.liveData.observe(this, observer)
    }
}
