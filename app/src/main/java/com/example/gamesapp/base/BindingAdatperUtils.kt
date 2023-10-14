package com.example.gamesapp.base

import android.widget.ImageView
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