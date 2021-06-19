package com.challenge.githubrepo.fragments

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.challenge.domain.model.GitRepoModel
import com.challenge.githubrepo.R

@BindingAdapter("forkText")
fun forkText(textView: TextView, item: GitRepoModel?) {
    item?.let {
        textView.text = it.forks.toString()
    }
}

@BindingAdapter("starText")
fun starText(textView: TextView, item: GitRepoModel?) {
    item?.let {
        textView.text = it.numberStars.toString()
    }
}

@BindingAdapter("context", "loadImage")
fun loadImage(imageView: ImageView, context: Context, item: GitRepoModel?) {
    item?.let {
        Glide.with(context)
            .load(it.photoOwner)
            .placeholder(R.drawable.ic_default_profile)
            .into(imageView)
    }
}