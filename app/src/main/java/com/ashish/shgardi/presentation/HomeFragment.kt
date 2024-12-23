package com.ashish.shgardi.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.shgardi.R
import com.ashish.shgardi.databinding.FragmentHomeBinding
import com.ashish.shgardi.presentation.adapters.PopularPeopleAdapter
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import com.ashish.shgardi.utils.Resources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainAdapter: PopularPeopleAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupRecyclerView()
        fetchPopularPeopleList()
    }

    private fun initViews() {
        viewModel.fetchPopularPeopleList()
    }

    private fun setupRecyclerView() {
        mainAdapter = PopularPeopleAdapter()
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!viewModel.isLoading && !viewModel.isLastPage && lastVisibleItem + 5 >= totalItemCount) {
                        viewModel.fetchPopularPeopleList()
                    }
                }
            })
        }
    }

    private fun fetchPopularPeopleList() {

        // observe data from view model
        lifecycleScope.launch {
            viewModel.popularPeopleList.collect { result ->
                when (result) {
                    is Resources.Loading -> {
                        // Show loading state
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resources.Success -> {
                        // Update the adapter with the new data
                        println(result.data?.results)
                        mainAdapter.submitList(result.data?.results)
                        binding.progressBar.visibility = View.GONE

                    }
                    is Resources.Error -> {
                        // Show error state
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
