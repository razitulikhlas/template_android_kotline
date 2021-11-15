package com.proyekakhir.paging3

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.paging3.adapter.MoviesAdapter
import com.proyekakhir.paging3.viewmodel.ViewModelMovies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var moviesAdapter: MoviesAdapter


    private val viewModel: ViewModelMovies by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rcv)

        lifecycleScope.launch {
            viewModel.getMovies().collect {
                Log.e("TAG", "onCreate: ${it.map { it.originalTitle.toString() }}")
                moviesAdapter.submitData(lifecycle, it)
                recyclerView.adapter = moviesAdapter
                Log.e("TAG", "onCreate: ${moviesAdapter.snapshot()}")
            }
        }


    }
}