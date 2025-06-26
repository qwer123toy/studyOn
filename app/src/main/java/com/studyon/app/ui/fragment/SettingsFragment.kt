package com.studyon.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.studyon.app.databinding.FragmentSettingsBinding
import com.studyon.app.ui.viewmodel.SettingsViewModel

class SettingsFragment : Fragment() {
    
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SettingsViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.notificationEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.switchNotification.isChecked = enabled
        }
        
        viewModel.selectedTheme.observe(viewLifecycleOwner) { theme ->
            when (theme) {
                "light" -> binding.radioLight.isChecked = true
                "dark" -> binding.radioDark.isChecked = true
            }
        }
        
        viewModel.message.observe(viewLifecycleOwner) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        
        viewModel.lastBackupDate.observe(viewLifecycleOwner) { date ->
            binding.textLastBackup.text = if (date.isEmpty()) {
                "백업 기록 없음"
            } else {
                "마지막 백업: $date"
            }
        }
    }

    private fun setupClickListeners() {
        // 알림 설정
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationEnabled(isChecked)
        }
        
        // 테마 설정
        binding.radioLight.setOnClickListener {
            viewModel.setTheme("light")
        }
        
        binding.radioDark.setOnClickListener {
            viewModel.setTheme("dark")
        }
        
        // 데이터 관리
        binding.buttonDataExport.setOnClickListener {
            viewModel.exportData()
        }
        
        binding.buttonDataImport.setOnClickListener {
            viewModel.importData()
        }
        
        binding.buttonDataReset.setOnClickListener {
            showResetConfirmDialog()
        }
        
        // 정보
        binding.buttonAppInfo.setOnClickListener {
            showAppInfoDialog()
        }
        
        binding.buttonShare.setOnClickListener {
            shareApp()
        }
    }

    private fun showResetConfirmDialog() {
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("데이터 초기화")
            .setMessage("모든 데이터가 삭제됩니다. 정말 초기화하시겠습니까?")
            .setPositiveButton("초기화") { _, _ ->
                viewModel.resetAllData()
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun showAppInfoDialog() {
        android.app.AlertDialog.Builder(requireContext())
            .setTitle("StudyOn 정보")
            .setMessage("버전: 1.0.0\n개발자: StudyOn Team\n\n시험 공부를 효율적으로 관리할 수 있는 앱입니다.")
            .setPositiveButton("확인", null)
            .show()
    }

    private fun shareApp() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "StudyOn 앱으로 공부 계획을 세워보세요!")
        }
        startActivity(Intent.createChooser(shareIntent, "앱 공유하기"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 