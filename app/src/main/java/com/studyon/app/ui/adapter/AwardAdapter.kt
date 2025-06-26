package com.studyon.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyon.app.R
import com.studyon.app.data.entity.AwardEntity
import com.studyon.app.databinding.ItemAwardBinding

class AwardAdapter : ListAdapter<AwardEntity, AwardAdapter.AwardViewHolder>(AwardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwardViewHolder {
        val binding = ItemAwardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AwardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AwardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AwardViewHolder(private val binding: ItemAwardBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(award: AwardEntity) {
            binding.textAwardTitle.text = award.title
            binding.textAwardDescription.text = award.description
            
            // 배지 타입에 따라 이미지 설정
            val imageResource = when (award.type) {
                "first_study" -> R.drawable.badge_first_study
                "streak_3" -> R.drawable.badge_3days_streak
                "streak_7" -> R.drawable.badge_7days_streak
                "streak_30" -> R.drawable.badge_30days_streak
                "total_10h" -> R.drawable.badge_10hours_total
                "total_50h" -> R.drawable.badge_50hours_total
                "total_100h" -> R.drawable.badge_100hours_total
                else -> R.drawable.ic_awards
            }
            binding.imageAwardIcon.setImageResource(imageResource)
            
            if (award.isEarned && award.earnedAt.isNotEmpty()) {
                binding.textAwardDate.text = "획득일: ${award.earnedAt}"
                binding.textAwardDate.visibility = android.view.View.VISIBLE
            } else {
                binding.textAwardDate.visibility = android.view.View.GONE
            }
            
            // 획득 여부에 따라 UI 변경
            if (award.isEarned) {
                binding.imageAwardIcon.alpha = 1.0f
                binding.cardView.alpha = 1.0f
            } else {
                binding.imageAwardIcon.alpha = 0.3f
                binding.cardView.alpha = 0.7f
            }
        }
    }

    class AwardDiffCallback : DiffUtil.ItemCallback<AwardEntity>() {
        override fun areItemsTheSame(oldItem: AwardEntity, newItem: AwardEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AwardEntity, newItem: AwardEntity): Boolean {
            return oldItem == newItem
        }
    }
} 