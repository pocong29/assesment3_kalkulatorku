package com.d3if4063.assesment3_kalkulatorku.ui.konversi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d3if4063.assesment3_kalkulatorku.databinding.FragmentKonversiBinding
import com.d3if4063.assesment3_kalkulatorku.network.ApiStatus
import com.d3if4063.assesment3_kalkulatorku.network.KonversiAdapter

class GaleriFragment : Fragment() {

    private lateinit var binding: FragmentKonversiBinding
    private lateinit var myAdapter: KonversiAdapter


    private val viewModel: GaleriViewModel by lazy {
        ViewModelProvider(this)[GaleriViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentKonversiBinding.inflate(layoutInflater, container, false)
        myAdapter = KonversiAdapter()
        with(binding.recyclerview) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.recyclerview) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })

        viewModel.scheduleUpdater(requireActivity().application)
    }
    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
