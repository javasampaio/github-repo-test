package com.challenge.githubrepo.fragments

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.githubrepo.MainActivity
import com.challenge.githubrepo.actionbar.ShowUpButtonListener

abstract class BaseFragment : Fragment() {

    private lateinit var showUpButtonListener: ShowUpButtonListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ShowUpButtonListener) {
            showUpButtonListener = context
        }
    }

    fun showUpButton() {
        showUpButtonListener.showUpButton()
    }

    fun hideUpButton() {
        showUpButtonListener.hideUpButton()
    }

    fun addDividerRecycler(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.apply {
            layoutManager = linearLayoutManager
        }
        DividerItemDecoration(requireContext(), linearLayoutManager.orientation).apply {
            recyclerView.addItemDecoration(this)
        }
    }

    fun setTitle(title: String) {
        activity?.let {
            (it as MainActivity).supportActionBar?.setTitle(title)
        }
    }
}