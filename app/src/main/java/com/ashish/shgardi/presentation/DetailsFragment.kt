package com.ashish.shgardi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ashish.shgardi.R
import com.ashish.shgardi.databinding.FragmentDetailsBinding
import com.ashish.shgardi.databinding.FragmentHomeBinding
import com.ashish.shgardi.presentation.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews(){



    }

}