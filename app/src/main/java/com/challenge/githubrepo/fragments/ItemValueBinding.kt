package com.challenge.githubrepo.fragments

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.challenge.domain.model.GitRepoModel
import com.challenge.githubrepo.R
import com.challenge.githubrepo.helper.DateHelper

@BindingAdapter("forkText")
fun forkText(textView: TextView, item: GitRepoModel?) {
    item?.let {
        textView.text = it.forks.toString()
        textView.contentDescription = textView.resources
            .getString(R.string.forks_description, it.forks.toString())
    }
}

@BindingAdapter("starText")
fun starText(textView: TextView, item: GitRepoModel?) {
    item?.let {
        textView.text = it.numberStars.toString()
        textView.contentDescription = textView.resources
            .getString(R.string.starts_description, it.numberStars.toString())
    }
}

@BindingAdapter("context", "loadImage")
fun loadImage(imageView: ImageView, context: Context, url: String?) {
    url?.let {
        Glide.with(context)
            .load(it)
            .placeholder(R.drawable.ic_default_profile)
            .into(imageView)
    }
}

@BindingAdapter("formatDate")
fun formatDate(textView: TextView, date: String?) {
    date?.let { dateString ->
        try {
            val dateValue = DateHelper.stringToLocalDate(dateString)
            dateValue?.let {
                val dateFormatted = DateHelper.dateToStringFormatted(it)
                textView.text = dateFormatted
                textView.contentDescription = textView.resources
                    .getString(R.string.pull_date_description, dateFormatted)
            }
        } catch (e: Exception) {
            textView.text = textView.resources.getString(R.string.error_date)
        }
    }
}
