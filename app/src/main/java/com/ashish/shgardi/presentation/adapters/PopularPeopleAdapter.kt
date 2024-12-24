package com.ashish.shgardi.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashish.shgardi.data.model.People
import com.ashish.shgardi.databinding.ItemPopularPersonBinding
import com.bumptech.glide.Glide

class PopularPeopleAdapter(
    private val onItemClick: (People) -> Unit
): ListAdapter<People, PopularPeopleAdapter.PopularPeopleViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPeopleViewHolder {
        val binding = ItemPopularPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularPeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularPeopleViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClick)
    }


    class PopularPeopleViewHolder(private val binding: ItemPopularPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: People, onItemClick: (People) -> Unit) {
            println("Person: ${person.profilePath}")
            binding.person = person
            binding.executePendingBindings()
            Glide.with(binding.root)
                .load(BASE_IMAGE_URL+person.profilePath)
                .into(binding.imageViewProfile)

            binding.root.setOnClickListener {
                onItemClick(person)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem == newItem
        }
    }
    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}