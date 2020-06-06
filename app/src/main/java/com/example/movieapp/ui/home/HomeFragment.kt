package com.example.movieapp.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
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
        movieAdapter = MovieListAdapter(MovieClickListener { viewModel.openDetails(it) })

        viewDataBinding.photosGrid.apply {
            adapter = movieAdapter
        }
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let{
                movieAdapter.submitList(it)
            }
        })
        viewModel.openDetails.observe(viewLifecycleOwner,Observer{
            it?.let{
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
                viewModel.completeNavigation()
            }
        })
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }


}
