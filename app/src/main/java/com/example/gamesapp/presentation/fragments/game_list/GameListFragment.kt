package com.example.gamesapp.presentation.fragments.game_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.gamesapp.base.BaseFragment
import com.example.gamesapp.R
import com.example.gamesapp.common.NavigationCommand
import com.example.gamesapp.databinding.FragmentGamesHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesListFragment : BaseFragment<FragmentGamesHomeBinding>() {

    override fun getLayoutRes() = R.layout.fragment_games_home

    override val viewModel: GameListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener = GameLiveListAdapter.OnClickListener { game ->
            viewModel.navigationCommand.value =
                NavigationCommand.To(
                    GamesListFragmentDirections.actionGamesHomeFragmentToDescriptionGameDetails(game.id)
                )
        }
        val adapter = GameLiveListAdapter(listener)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.gameLiveRecyclerview.adapter = adapter

        viewModel.gameList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

}