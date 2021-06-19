package com.challenge.githubrepo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.domain.model.GitPullModel
import com.challenge.githubrepo.databinding.PullItemLayoutBinding

class GitPullAdapter : RecyclerView.Adapter<GitPullAdapter.PullViewHolder>() {

    private var items: MutableList<GitPullModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        val binding = PullItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PullViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<GitPullModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class PullViewHolder(private val binding: PullItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitPullModel) {
            binding.pull = item
        }
    }
}