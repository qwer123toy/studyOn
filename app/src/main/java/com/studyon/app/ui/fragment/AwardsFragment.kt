package com.studyon.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.studyon.app.databinding.FragmentAwardsBinding
import com.studyon.app.ui.adapter.AwardAdapter
import com.studyon.app.ui.viewmodel.AwardViewModel

class AwardsFragment : Fragment() {
    
    private var _binding: FragmentAwardsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: AwardViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var awardAdapter: AwardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        awardAdapter = AwardAdapter()
        binding.recyclerViewAwards.apply {
            adapter = awardAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setupObservers() {
        viewModel.allAwards.observe(viewLifecycleOwner) { awards ->
            awardAdapter.submitList(awards)
            
            if (awards.isEmpty()) {
                binding.recyclerViewAwards.visibility = View.GONE
                binding.textEmptyMessage.visibility = View.VISIBLE
            } else {
                binding.recyclerViewAwards.visibility = View.VISIBLE
                binding.textEmptyMessage.visibility = View.GONE
            }
        }
        
        viewModel.earnedAwardsCount.observe(viewLifecycleOwner) { count ->
            val totalCount = viewModel.allAwards.value?.size ?: 0
            binding.textAwardProgress.text = "획득한 배지: $count / $totalCount"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 