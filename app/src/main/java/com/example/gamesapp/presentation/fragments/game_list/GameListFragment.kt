package com.example.gamesapp.presentation.fragments.game_list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
        viewModel.errorText.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        val menuHost: MenuHost = requireActivity()
        filterGamesMenu(menuHost)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGameList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun filterGamesMenu(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.games_filter_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.filter_pc_menu -> {
                        viewModel.filterAllGamesByPlatform(PlatformType.PC.platform)
                    }

                    R.id.filter_browser_menu -> {
                        viewModel.filterAllGamesByPlatform(PlatformType.BROWSER.platform)
                    }

                    R.id.filter_all_menu -> {
                        viewModel.filterAllGamesByPlatform(PlatformType.PC.platform)
                        viewModel.filterAllGamesByPlatform(PlatformType.BROWSER.platform)
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}