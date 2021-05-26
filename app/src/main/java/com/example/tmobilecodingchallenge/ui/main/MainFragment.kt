package com.example.tmobilecodingchallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmobilecodingchallenge.databinding.MainFragmentBinding
import com.example.tmobilecodingchallenge.ui.main.adapters.CardAdapter

class MainFragment : Fragment(){

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.response.observe(viewLifecycleOwner, Observer {

            binding.responseRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CardAdapter(it.page.cards)
            }

        })


    }


}