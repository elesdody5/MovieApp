package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.bindImage
import com.example.movieapp.data.entity.Movie

/**
 * Adapter for the list of repositories.
 */
class MovieListAdapter(val onClickListener: MovieClickListener) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { movie?.let { onClickListener.onClick(it) } }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.icon)
        private val type: TextView = view.findViewById(R.id.type)
        fun bind(movie: Movie?) {
            name.text = movie?.name
            type.text = movie?.type
            val icon = if (movie?.type == "VIDEO") {
                R.drawable.ic_baseline_videocam_24
            } else R.drawable.ic_baseline_picture_as_pdf_24

            bindImage(image, icon)
        }

        companion object {
            fun create(parent: ViewGroup): MovieViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                return MovieViewHolder(view)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieClickListener(val onClick: (movie: Movie) -> Unit)
