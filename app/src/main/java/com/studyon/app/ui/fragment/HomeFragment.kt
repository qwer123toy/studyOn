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
import com.studyon.app.databinding.FragmentHomeBinding
import com.studyon.app.ui.adapter.TodoAdapter
import com.studyon.app.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            setupRecyclerView()
            setupObservers()
            setupClickListeners()
        } catch (e: Exception) {
            android.util.Log.e("HomeFragment", "Error in onViewCreated", e)
        }
    }

    private fun setupRecyclerView() {
        try {
            todoAdapter = TodoAdapter { todoId, isChecked ->
                viewModel.toggleTodoStatus(todoId, isChecked)
            }
            
            binding.recyclerViewTodos.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = todoAdapter
            }
        } catch (e: Exception) {
            android.util.Log.e("HomeFragment", "Error setting up RecyclerView", e)
        }
    }

    private fun setupObservers() {
        viewModel.currentDate.observe(viewLifecycleOwner) { date ->
            binding.textCurrentDate.text = date
        }
        
        viewModel.greeting.observe(viewLifecycleOwner) { greeting ->
            binding.textGreeting.text = greeting
        }
        
        viewModel.todayStudyTime.observe(viewLifecycleOwner) { time ->
            binding.textStudyTime.text = viewModel.getTodayStudyTimeString()
        }
        
        viewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            todoAdapter.submitList(todos)
            
            // 루틴이 없을 때 안내 메시지 표시
            if (todos.isEmpty()) {
                binding.textEmptyMessage.visibility = View.VISIBLE
                binding.recyclerViewTodos.visibility = View.GONE
            } else {
                binding.textEmptyMessage.visibility = View.GONE
                binding.recyclerViewTodos.visibility = View.VISIBLE
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonStartRoutine.setOnClickListener {
            findNavController().navigate(R.id.navigation_routine)
        }
        
        binding.buttonAddGoal.setOnClickListener {
            findNavController().navigate(R.id.navigation_goals)
        }
        
        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 