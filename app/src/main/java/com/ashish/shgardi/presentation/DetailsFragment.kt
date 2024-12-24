package com.ashish.shgardi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.shgardi.R
import com.ashish.shgardi.databinding.FragmentDetailsBinding
import com.ashish.shgardi.databinding.FragmentHomeBinding
import com.ashish.shgardi.presentation.adapters.PersonDetailsAdapter
import com.ashish.shgardi.presentation.adapters.PopularPeopleAdapter
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private val personDetailsAdapter = PersonDetailsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupBackButton()
    }

    private fun initViews(){

        println("Selected Person: ${viewModel.selectedPerson.name}")
        with(binding){
            personName.text = viewModel.selectedPerson.name

            Glide.with(requireContext())
                .load(PopularPeopleAdapter.BASE_IMAGE_URL + viewModel.selectedPerson.profilePath)
                .into(imageView)

        }


        binding.personContributionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = personDetailsAdapter
        }

        // Assuming you have a method to get the selected person and their knownFor list
        val selectedPerson = viewModel.selectedPerson
        selectedPerson.knownFor?.let { personDetailsAdapter.submitList(it) }


    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}