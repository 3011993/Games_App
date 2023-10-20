package com.example.gamesapp.base

import android.transition.Visibility
import android.view.View
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesapp.domain.model.GameModel
import com.example.gamesapp.presentation.fragments.game_list.GameLiveListAdapter

@BindingAdapter("load_image")
fun loadImage(imageView: ImageView, image: String?) {
    image?.let {
        Glide.with(imageView)
            .load(image)
            .into(imageView)
    }
}

@BindingAdapter("submit_liveGameList")
fun RecyclerView.submitList(list: List<GameModel>?) {
    val adapter = this.adapter as? GameLiveListAdapter
    adapter?.submitList(list)
}

@BindingAdapter("setVisibility")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.INVISIBLE
}