package com.challenge.githubrepo.fragments.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.common.state.LoadingState
import com.challenge.domain.model.GitRepoModel
import com.challenge.githubrepo.R
import com.challenge.githubrepo.adapter.GitRepoAdapter
import com.challenge.githubrepo.databinding.GitRepoLayoutBinding
import com.challenge.githubrepo.fragments.BaseFragment
import com.challenge.githubrepo.helper.showSnackbar
import org.koin.android.ext.android.inject

class GitRepoFragment : BaseFragment() {

    private lateinit var binding: GitRepoLayoutBinding

    private lateinit var repoAdapter: GitRepoAdapter

    private val repoViewMode: GitRepoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GitRepoLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        setupEndScrollListener()
        hideUpButton()
        setTitle(getString(R.string.repo_title))
    }

    private fun setupEndScrollListener() {
        binding.repoRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val isEnd = layoutManager
                    .findLastCompletelyVisibleItemPosition() == (recyclerView.adapter?.itemCount ?: 0).minus(1)
                if (isEnd) {
                    repoViewMode.loadRepoByPage()
                }
            }
        })
    }

    private fun setupAdapter() {
        repoAdapter = GitRepoAdapter(listenerItemRemove)
        addDividerRecycler(binding.repoRecycler)
        binding.repoRecycler.apply {
            adapter = repoAdapter
        }
    }

    private fun setupObservers() {
        repoViewMode.loaded.observe(viewLifecycleOwner) {
            repoAdapter.addItems(it)
        }

        repoViewMode.loading.observe(viewLifecycleOwner) {
            binding
                .repoProgress
                .visibility = if (it == LoadingState.Active) View.VISIBLE else View.GONE
        }

        repoViewMode.error.observe(viewLifecycleOwner) {
            it?.let {
                showSnackbar(binding.root, it.message)
            }
        }
    }

    private val listenerItemRemove: (GitRepoModel) -> Unit = {
        findNavController().navigate(
            GitRepoFragmentDirections.actionGitRepoFragmentToGitPullFragment(
                it.nameOwner,
                it.name
            )
        )
    }
}