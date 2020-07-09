package dev.pinaki.desipe.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected lateinit var binding: B

    //region callback methods (abstract and open)
    protected abstract fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B

    protected abstract fun initializeView()

    protected abstract fun observeDataAndActions()

    protected abstract fun getToolbarInstance(): Toolbar?

    protected open fun loadData() {

    }

    protected open fun performActionsOnSavedState(savedStateHandle: SavedStateHandle?) {

    }

    protected open fun getToolbarTitle(): String? = null

    protected open fun fragmentHasOptionsMenu(): Boolean {
        return false
    }
    //endregion

    //region lifecycle related methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(fragmentHasOptionsMenu())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initializeBinding(inflater,container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getToolbarInstance()?.let {
            (requireActivity() as AppCompatActivity).setSupportActionBar(getToolbarInstance())
            (requireActivity() as AppCompatActivity).title = getToolbarTitle()
        }

        initializeView()
        loadData()
        observeDataAndActions()
        performActionsOnSavedState(findNavController().currentBackStackEntry?.savedStateHandle)
    }
    //endregion
}