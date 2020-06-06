package com.example.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.bindImage
import com.example.movieapp.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.list_item.*


class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_details, container, false)
        val movie = args.movie
        bindImage(poster,movie.posterPath)
        name.text = movie.title
        rating.text = movie.rating.toString()
        release_date.text = movie.releaseDate

        return view
    }
}