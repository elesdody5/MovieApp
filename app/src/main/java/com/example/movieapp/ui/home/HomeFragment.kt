package com.example.movieapp.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.database.DatabaseUtils
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var viewDataBinding: FragmentHomeBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewDataBinding = FragmentHomeBinding.bind(view)
        movieAdapter = MovieListAdapter(MovieClickListener { viewModel.onItemClicked(it) })

        viewDataBinding.moviesRv.apply {
            adapter = movieAdapter
        }
        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewDataBinding.progressCircular.visibility = GONE
                movieAdapter.submitList(it)
            }
        })

        viewModel.networkError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })

        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            it?.let {
                HomeFragmentDirections.actionHomeFragmentToDownloadFragment(it)
            }
        })
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }


}
