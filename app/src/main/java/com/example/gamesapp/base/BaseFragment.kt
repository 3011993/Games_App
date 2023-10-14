package com.example.gamesapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gamesapp.common.NavigationCommand

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {


    abstract val viewModel: BaseViewModel

    @LayoutRes
    abstract fun getLayoutRes(): Int

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayoutRes(), container, false).apply {
            binding = this
        }.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.showErrorToast.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.navigationCommand.observe(this) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId,
                    false
                )
            }

        }
    };

}