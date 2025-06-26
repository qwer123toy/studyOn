package com.studyon.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyon.app.R
import com.studyon.app.databinding.FragmentGoalsBinding
import com.studyon.app.ui.adapter.GoalAdapter
import com.studyon.app.ui.viewmodel.GoalViewModel

class GoalsFragment : Fragment() {
    
    private var _binding: FragmentGoalsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: GoalViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var goalAdapter: GoalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        goalAdapter = GoalAdapter { goal ->
            // 목표 삭제 콜백
            viewModel.deleteGoal(goal)
        }
        
        binding.recyclerViewGoals.apply {
            adapter = goalAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers() {
        viewModel.allGoals.observe(viewLifecycleOwner) { goals ->
            goalAdapter.submitList(goals)
            
            // 빈 상태 처리
            if (goals.isEmpty()) {
                binding.recyclerViewGoals.visibility = View.GONE
                binding.textEmptyMessage.visibility = View.VISIBLE
            } else {
                binding.recyclerViewGoals.visibility = View.VISIBLE
                binding.textEmptyMessage.visibility = View.GONE
            }
        }
        
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupClickListeners() {
        binding.fabAddGoal.setOnClickListener {
            findNavController().navigate(R.id.action_goals_to_add_goal)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 