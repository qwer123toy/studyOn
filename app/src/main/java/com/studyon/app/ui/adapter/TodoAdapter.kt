package com.studyon.app.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyon.app.data.entity.TodoEntity
import com.studyon.app.databinding.ItemTodoBinding

class TodoAdapter(
    private val onTodoChecked: (Int, Boolean) -> Unit
) : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(todo: TodoEntity) {
            binding.apply {
                textTodoContent.text = todo.content
                textTodoDueDate.text = "마감: ${todo.dueDate}"
                checkboxTodo.isChecked = todo.isDone
                
                // 반복 타입 표시
                when (todo.repeatType) {
                    "daily" -> textRepeatType.text = "매일"
                    "weekly" -> textRepeatType.text = "매주"
                    else -> textRepeatType.text = ""
                }
                
                // 체크박스 클릭 이벤트
                checkboxTodo.setOnCheckedChangeListener { _, isChecked ->
                    onTodoChecked(todo.id, isChecked)
                }
                
                // 완료된 항목은 스타일 변경
                if (todo.isDone) {
                    textTodoContent.alpha = 0.6f
                    textTodoDueDate.alpha = 0.6f
                    textRepeatType.alpha = 0.6f
                    // 취소선 추가
                    textTodoContent.paintFlags = textTodoContent.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    textTodoContent.alpha = 1.0f
                    textTodoDueDate.alpha = 1.0f
                    textRepeatType.alpha = 1.0f
                    // 취소선 제거
                    textTodoContent.paintFlags = textTodoContent.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<TodoEntity>() {
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem == newItem
        }
    }
} 