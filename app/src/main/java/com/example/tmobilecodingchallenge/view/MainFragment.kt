package com.example.tmobilecodingchallenge.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tmobilecodingchallenge.R
import com.example.tmobilecodingchallenge.adapter.CardAdapter
import com.example.tmobilecodingchallenge.databinding.FragmentMainBinding
import com.example.tmobilecodingchallenge.models.Card
import com.example.tmobilecodingchallenge.repo.CardRepo
import com.example.tmobilecodingchallenge.viewmodel.CardViewModel
import com.example.tmobilecodingchallenge.viewmodel.CardViewModelFactory

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: CardViewModel by viewModels { CardViewModelFactory(CardRepo()) }

    private val cardObserver = Observer<List<Card>> {
        binding.responseRv.adapter = CardAdapter(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        viewModel.cardsLD.observe(viewLifecycleOwner, cardObserver)
    }
}