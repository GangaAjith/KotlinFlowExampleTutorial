package com.gangaown.kotlinflowexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gangaown.kotlinflowexample.databinding.ActivityMainBinding
import com.gangaown.kotlinflowexample.ui.HomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity:AppCompatActivity() {

    private val  homeViewModel:HomeViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collectLatestLifecycleFlow(homeViewModel.stateFlow){
            binding.tvMsg.text = it.toString()
        }
        binding.button.setOnClickListener {
            homeViewModel.incrementCounter()
        }
    }

    private fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect:suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                    flow.collectLatest(collect)
            }
        }
    }
}