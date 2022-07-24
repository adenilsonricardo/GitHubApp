package com.example.githubrepositoryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepositoryapp.data.model.Repo
import com.example.githubrepositoryapp.databinding.ItemRepoBinding

class RepoListAdapter: ListAdapter<Repo, RepoListAdapter.ViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater, parent, false)
        return RecyclerView.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRepoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repo) {
            binding.tvRepoName.text = item.name
            binding.tvRepoDescription.text = item.description
            binding.tvRepoLanguage.text = item.language
            binding.chipStars.text = item.stargazersCount.toString()
            Glide.with(binding.root.context)
                .load(item.owner.avatarURL).into(binding.ivOwner)
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id
}