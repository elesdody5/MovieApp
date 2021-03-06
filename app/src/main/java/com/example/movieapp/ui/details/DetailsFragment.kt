package com.example.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.bindImage
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.list_item.*


class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val movie = args.movie
        viewDataBinding = FragmentDetailsBinding.bind(view)
        viewDataBinding.apply {
            bindImage(poster, movie.posterPath)
            name.text = movie.title
            rating.text = movie.rating.toString()
            releaseDate.text = movie.releaseDate
            overview.text = movie.overview
        }
        return view
    }
}