package com.kotlinroomflow.ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kotlinroomflow.R
import kotlinx.coroutines.launch

class Ex2_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2_main)
        setupViewModel()
        setupObserver()
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        Toast.makeText(applicationContext,""+it.data.size,Toast.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
        }
    }
    private lateinit var viewModel: RoomDBViewModel
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[RoomDBViewModel::class.java]
    }
}