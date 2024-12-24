package com.ashish.shgardi.presentation

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.shgardi.R
import com.ashish.shgardi.databinding.FragmentHomeBinding
import com.ashish.shgardi.presentation.adapters.PopularPeopleAdapter
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var mainAdapter: PopularPeopleAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupRecyclerView()
        observePeopleList()
    }

    private fun initViews() {
        setupSearchView()
    }

    private fun setupRecyclerView() {
        mainAdapter = PopularPeopleAdapter(
            onItemClick = {
               viewModel.selectedPerson = it
                findNavController().navigate(R.id.detailsFragment)
            }
        )
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

    private fun observePeopleList() {

        // observe data from view model
        lifecycleScope.launch {
            viewModel.popularPeopleList.collect { result ->

                val showProgress = viewModel.isLoading && result.isNullOrEmpty()
                binding.progressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
                mainAdapter.submitList(result)
            }

        }
        viewModel.filteredPeopleList.observe(viewLifecycleOwner) { result ->
            mainAdapter.submitList(result)
        }

    }

    private fun setupSearchView() {
        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("newText: $newText")
                if (newText != null && newText.length > 1) {
                    viewModel.searchPeople(newText)
                } else {
                    mainAdapter.submitList(viewModel.popularPeopleList.value)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
