package com.example.gamesapp.presentation.fragments.game_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.gamesapp.base.BaseViewHolder
import com.example.gamesapp.R
import com.example.gamesapp.databinding.LiveGameRowBinding
import com.example.gamesapp.domain.model.GameModel


class GameLiveListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<GameModel, GameLiveListAdapter.GameLiveViewHolder>(
        diffCallBack
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameLiveViewHolder {
        return GameLiveViewHolder(
            LayoutInflater.from(parent.context).inflate(
                (R.layout.live_game_row),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GameLiveViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.game = currentItem
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            onClickListener.setOnClickListener(currentItem)
        }


    }

    class OnClickListener(val clickListener: (game: GameModel) -> Unit) {
        fun setOnClickListener(game: GameModel) = clickListener(game)

    }


    class GameLiveViewHolder(view: View) : BaseViewHolder<LiveGameRowBinding>(view)


    companion object {
        private val diffCallBack: DiffUtil.ItemCallback<GameModel> =
            object : DiffUtil.ItemCallback<GameModel>() {
                override fun areItemsTheSame(
                    oldItem: GameModel,
                    newItem: GameModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: GameModel,
                    newItem: GameModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }


}