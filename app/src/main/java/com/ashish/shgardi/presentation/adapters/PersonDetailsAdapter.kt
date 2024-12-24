package com.ashish.shgardi.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.shgardi.data.model.KnownFor
import com.ashish.shgardi.databinding.ItemPersonImageBinding
import com.bumptech.glide.Glide

class PersonDetailsAdapter : RecyclerView.Adapter<PersonDetailsAdapter.KnownForViewHolder>() {

    private var knownForList: List<KnownFor?> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnownForViewHolder {
        val binding = ItemPersonImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KnownForViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KnownForViewHolder, position: Int) {
        holder.bind(knownForList[position])
    }

    override fun getItemCount(): Int = knownForList.size

    fun submitList(list: List<KnownFor?>) {
        knownForList = list
        notifyDataSetChanged()
    }

    inner class KnownForViewHolder(private val binding: ItemPersonImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(knownFor: KnownFor?) {
            knownFor?.let {
                binding.knownForTitle.text = it.title ?: it.name
                Glide.with(binding.knownForImage.context)
                    .load(BASE_IMAGE_URL + it.posterPath)
                    .into(binding.knownForImage)
                with(binding) {
                    knownForImage.clipToOutline = true

                    overview.text = "Overview: ${it.overview} "
                    name.text = "Name: ${it.name}"
                    releaseDate.text = "Release Date: ${it.releaseDate}"
                    voteCount.text = "Vote Count: ${it.voteCount}"
                    originalLanguage.text = "Original Language: ${it.originalLanguage}"
                    firstAirDate.text = "First Air Date: ${it.firstAirDate}"
                    adult.text = "Adult: ${it.adult}"
                }
            }
        }
    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}