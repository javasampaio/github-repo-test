package com.challenge.githubrepo.fragments.pull

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.challenge.common.state.LoadingState
import com.challenge.githubrepo.MainActivity
import com.challenge.githubrepo.actionbar.ShowUpButtonListener
import com.challenge.githubrepo.adapter.GitPullAdapter
import com.challenge.githubrepo.adapter.GitRepoAdapter
import com.challenge.githubrepo.databinding.GitPullLayoutBinding
import com.challenge.githubrepo.helper.showSnackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class GitPullFragment : Fragment() {

    private lateinit var binding: GitPullLayoutBinding

    private lateinit var gitPullViewModel: GitPullViewModel

    private lateinit var pullAdapter: GitPullAdapter

    private val args: GitPullFragmentArgs by navArgs()

    private lateinit var showUpButtonListener: ShowUpButtonListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitPullLayoutBinding.inflate(inflater, container, false)

        gitPullViewModel = getViewModel {
            parametersOf(
                args.author,
                args.repo
            )
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ShowUpButtonListener) {
            showUpButtonListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        showUpButtonListener.showUpButton()
    }

    private fun setupAdapter() {
        pullAdapter = GitPullAdapter()
        binding.pullRecycler.apply {
            adapter = pullAdapter
        }
    }

    private fun setupObservers() {
        gitPullViewModel.loaded.observe(viewLifecycleOwner) {
            pullAdapter.addItems(it)
        }

        gitPullViewModel.loading.observe(viewLifecycleOwner) {
            binding
                .pullProgress
                .visibility = if (it == LoadingState.Active) View.VISIBLE else View.GONE
        }

        gitPullViewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                showSnackbar(binding.root, it.message)
            }
        }
    }

}