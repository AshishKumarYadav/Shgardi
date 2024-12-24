package com.ashish.shgardi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ashish.shgardi.R
import com.ashish.shgardi.databinding.FragmentDetailsBinding
import com.ashish.shgardi.databinding.FragmentHomeBinding
import com.ashish.shgardi.presentation.adapters.PersonDetailsAdapter
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val personDetailsAdapter = PersonDetailsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews(){

        binding.personContributionsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = personDetailsAdapter
        }

        // Assuming you have a method to get the selected person and their knownFor list
        val selectedPerson = viewModel.selectedPerson
        selectedPerson.knownFor?.let { personDetailsAdapter.submitList(it) }


    }

}