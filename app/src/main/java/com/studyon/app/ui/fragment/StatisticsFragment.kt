package com.studyon.app.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.studyon.app.R
import com.studyon.app.databinding.FragmentStatisticsBinding
import com.studyon.app.ui.viewmodel.StatisticsViewModel

class StatisticsFragment : Fragment() {
    
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: StatisticsViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupChart()
        setupObservers()
        setupClickListeners()
    }

    private fun setupChart() {
        binding.barChart.apply {
            description.isEnabled = false
            setDrawGridBackground(false)
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            setPinchZoom(false)
            setScaleEnabled(false)
            
            // X축 설정
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
                textColor = Color.parseColor("#78909C")
            }
            
            // Y축 설정
            axisLeft.apply {
                setDrawGridLines(true)
                gridColor = Color.parseColor("#E0E0E0")
                textColor = Color.parseColor("#78909C")
                axisMinimum = 0f
            }
            
            axisRight.isEnabled = false
            legend.isEnabled = false
        }
    }

    private fun setupObservers() {
        viewModel.selectedPeriod.observe(viewLifecycleOwner) { period ->
            binding.chipWeekly.isChecked = period == "weekly"
            binding.chipMonthly.isChecked = period == "monthly"
        }
        
        viewModel.weeklyStats.observe(viewLifecycleOwner) { weeklyData ->
            if (viewModel.selectedPeriod.value == "weekly") {
                updateChart(weeklyData)
            }
        }
        
        viewModel.monthlyStats.observe(viewLifecycleOwner) { monthlyData ->
            if (viewModel.selectedPeriod.value == "monthly") {
                updateChart(monthlyData)
            }
        }
        
        viewModel.totalStudyTime.observe(viewLifecycleOwner) { totalTime ->
            binding.textTotalTime.text = viewModel.formatDuration(totalTime)
        }
        
        viewModel.averageStudyTime.observe(viewLifecycleOwner) { averageTime ->
            binding.textAverageTime.text = viewModel.formatDuration(averageTime)
        }
        
        viewModel.studyStreak.observe(viewLifecycleOwner) { streak ->
            binding.textStudyStreak.text = "${streak}일"
        }
    }

    private fun setupClickListeners() {
        binding.chipWeekly.setOnClickListener {
            viewModel.setPeriod("weekly")
        }
        
        binding.chipMonthly.setOnClickListener {
            viewModel.setPeriod("monthly")
        }
    }

    private fun updateChart(data: List<Pair<String, Int>>) {
        val entries = data.mapIndexed { index, (_, duration) ->
            BarEntry(index.toFloat(), duration.toFloat())
        }
        
        val labels = data.map { it.first }
        
        val dataSet = BarDataSet(entries, "공부 시간").apply {
            color = Color.parseColor("#66BB6A")
            valueTextColor = Color.parseColor("#37474F")
            valueTextSize = 10f
        }
        
        val barData = BarData(dataSet)
        barData.barWidth = 0.8f
        
        binding.barChart.apply {
            this.data = barData
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            animateY(1000)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 