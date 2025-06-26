package com.studyon.app.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.studyon.app.databinding.FragmentAddGoalBinding
import com.studyon.app.ui.viewmodel.GoalViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddGoalFragment : Fragment() {
    
    private var _binding: FragmentAddGoalBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: GoalViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }
    private var selectedDeadline: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddGoalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.buttonSelectDate.setOnClickListener {
            showDatePicker()
        }
        
        binding.buttonSave.setOnClickListener {
            saveGoal()
        }
        
        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                if (message.contains("추가되었습니다")) {
                    findNavController().navigateUp()
                }
            }
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.buttonSave.isEnabled = !isLoading
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDeadline = formatter.format(selectedDate.time)
                
                val displayFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
                binding.textSelectedDate.text = displayFormatter.format(selectedDate.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
            show()
        }
    }

    private fun saveGoal() {
        val title = binding.editTextGoalTitle.text.toString().trim()
        val tag = binding.editTextTag.text.toString().trim()
        
        when {
            title.isEmpty() -> {
                binding.editTextGoalTitle.error = "목표명을 입력해주세요"
                return
            }
            tag.isEmpty() -> {
                binding.editTextTag.error = "태그를 입력해주세요"
                return
            }
            selectedDeadline.isEmpty() -> {
                Toast.makeText(context, "마감일을 선택해주세요", Toast.LENGTH_SHORT).show()
                return
            }
        }
        
        viewModel.insertGoal(title, tag, selectedDeadline)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 