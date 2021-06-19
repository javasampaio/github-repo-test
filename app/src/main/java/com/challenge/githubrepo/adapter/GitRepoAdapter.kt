package com.challenge.githubrepo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.domain.model.GitRepoModel
import com.challenge.githubrepo.databinding.RepoItemLayoutBinding

class GitRepoAdapter(private val listener: (GitRepoModel) -> Unit) :
    RecyclerView.Adapter<GitRepoAdapter.RepoViewHolder>() {

    private var items: MutableList<GitRepoModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = RepoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<GitRepoModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(private val binding: RepoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitRepoModel, listener: (GitRepoModel) -> Unit) {
            binding.repo = item
            binding.root.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}