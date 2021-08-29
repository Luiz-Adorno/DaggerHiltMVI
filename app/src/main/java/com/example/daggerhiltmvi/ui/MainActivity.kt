package com.example.daggerhiltmvi.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.daggerhiltmvi.R
import com.example.daggerhiltmvi.databinding.ActivityMainBinding
import com.example.daggerhiltmvi.model.Blog
import com.example.daggerhiltmvi.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

        setContentView(binding.root)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            binding.text.text = message
        } else {
            binding.text.text = "Erro desconhecido"
        }
    }

    private fun displayProgressBar(isDIsplayed: Boolean) {
        binding.progressBar.visibility =
            if (isDIsplayed) View.VISIBLE
            else View.GONE
    }
    private fun appendBlogTitles(blogs: List<Blog>){
        val sb = StringBuilder()
        for(blog in blogs){
            sb.append(blog.title + "\n")
        }
        binding.text.text = sb.toString()
    }
}