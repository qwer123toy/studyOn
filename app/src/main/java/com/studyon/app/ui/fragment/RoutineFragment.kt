package com.studyon.app.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.studyon.app.R
import com.studyon.app.databinding.FragmentRoutineBinding
import com.studyon.app.ui.viewmodel.RoutineViewModel
import java.text.SimpleDateFormat
import java.util.*

class RoutineFragment : Fragment() {
    
    private var _binding: FragmentRoutineBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: RoutineViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSpinner()
        setupObservers()
        setupClickListeners()
    }

    private fun setupSpinner() {
        viewModel.allGoals.observe(viewLifecycleOwner) { goals ->
            val goalTitles = goals.map { "${it.title} (${it.tag})" }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                goalTitles
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerGoals.adapter = adapter
            
            if (goals.isNotEmpty()) {
                viewModel.setSelectedGoalId(goals[0].id)
            }
        }
    }

    private fun setupObservers() {
        viewModel.selectedDate.observe(viewLifecycleOwner) { date ->
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val displayFormatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
            try {
                val dateObj = formatter.parse(date)
                binding.textSelectedDate.text = dateObj?.let { displayFormatter.format(it) }
            } catch (e: Exception) {
                binding.textSelectedDate.text = date
            }
        }
        
        viewModel.studyDuration.observe(viewLifecycleOwner) { duration ->
            binding.textDuration.text = viewModel.formatDuration(duration)
        }
        
        viewModel.isTimerRunning.observe(viewLifecycleOwner) { isRunning ->
            binding.buttonStartTimer.text = if (isRunning) "일시정지" else "시작"
            binding.buttonStopTimer.isEnabled = isRunning
        }
        
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonSelectDate.setOnClickListener {
            showDatePicker()
        }
        
        binding.buttonStartTimer.setOnClickListener {
            if (viewModel.isTimerRunning.value == true) {
                viewModel.stopTimer()
            } else {
                viewModel.startTimer()
            }
        }
        
        binding.buttonStopTimer.setOnClickListener {
            viewModel.resetTimer()
        }
        
        binding.buttonManualInput.setOnClickListener {
            showManualInputDialog()
        }
        
        binding.buttonSave.setOnClickListener {
            saveRoutine()
        }
        
        binding.spinnerGoals.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.allGoals.value?.let { goals ->
                    if (position < goals.size) {
                        viewModel.setSelectedGoalId(goals[position].id)
                    }
                }
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        })
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dateString = formatter.format(selectedDate.time)
                viewModel.setSelectedDate(dateString)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showManualInputDialog() {
        val builder = android.app.AlertDialog.Builder(requireContext())
        val input = android.widget.EditText(requireContext())
        input.hint = "공부한 시간 (분)"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        
        builder.setTitle("시간 직접 입력")
            .setView(input)
            .setPositiveButton("확인") { _, _ ->
                val minutes = input.text.toString().toIntOrNull()
                if (minutes != null && minutes > 0) {
                    viewModel.setStudyDuration(minutes)
                } else {
                    Toast.makeText(context, "올바른 시간을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun saveRoutine() {
        val memo = binding.editTextMemo.text.toString().trim()
        viewModel.setMemo(memo)
        viewModel.saveRoutineLog()
        
        // 입력 필드 초기화
        binding.editTextMemo.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 