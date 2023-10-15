package com.example.gamesapp.presentation.fragments.specific_game

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.gamesapp.R
import com.example.gamesapp.base.BaseFragment
import com.example.gamesapp.common.Constants
import com.example.gamesapp.databinding.FragmentDescriptionGameDetailsBinding
import com.example.gamesapp.domain.model.GameModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecificGameFragment : BaseFragment<FragmentDescriptionGameDetailsBinding>() {

    override val viewModel: SpecificGameViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_description_game_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this


        val id = SpecificGameFragmentArgs.fromBundle(requireArguments()).id

        viewModel.getSpecificGame(id.toString())


        viewModel.specificGame.observe(viewLifecycleOwner) {
            binding.specificGame = it
            Log.i("viewModel","it")
        }


    }

}
