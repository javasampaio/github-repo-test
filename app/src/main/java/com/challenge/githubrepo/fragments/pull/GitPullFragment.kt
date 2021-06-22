package com.challenge.githubrepo.fragments.pull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.challenge.common.state.LoadingState
import com.challenge.githubrepo.adapter.GitPullAdapter
import com.challenge.githubrepo.databinding.GitPullLayoutBinding
import com.challenge.githubrepo.fragments.BaseFragment
import com.challenge.githubrepo.helper.showSnackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class GitPullFragment : BaseFragment() {

    private lateinit var binding: GitPullLayoutBinding

    private lateinit var gitPullViewModel: GitPullViewModel

    private lateinit var pullAdapter: GitPullAdapter

    private val args: GitPullFragmentArgs by navArgs()

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        showUpButton()
        setTitle(args.repo)
    }

    private fun setupAdapter() {
        pullAdapter = GitPullAdapter()
        addDividerRecycler(binding.pullRecycler)
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
                showSnackbar(it.message)
            }
        }
    }

}