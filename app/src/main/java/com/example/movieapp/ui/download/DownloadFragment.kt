package com.example.movieapp.ui.download

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movieapp.MovieApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDownloadBinding
import com.example.movieapp.ui.home.HomeViewModel
import javax.inject.Inject


class DownloadFragment : Fragment() {

    val args = navArgs<DownloadFragmentArgs>()

    lateinit var binding: FragmentDownloadBinding

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    private val viewModel by viewModels<DownloadViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_download, container, false)
        binding = FragmentDownloadBinding.bind(view)

        args.value.movie.apply {
//            binding.name.text = name
//            viewModel.downloadFile(this)
        }

        return view
    }


}