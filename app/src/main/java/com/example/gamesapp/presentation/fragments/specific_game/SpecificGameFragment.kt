package com.example.gamesapp.presentation.fragments.specific_game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.gamesapp.R
import com.example.gamesapp.base.BaseFragment
import com.example.gamesapp.databinding.FragmentDescriptionGameDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecificGameFragment : BaseFragment<FragmentDescriptionGameDetailsBinding>() {

    override val viewModel: SpecificGameViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_description_game_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val game = SpecificGameFragmentArgs.fromBundle(requireArguments()).game
        binding.lifecycleOwner = this
        binding.game = game



    }

}
