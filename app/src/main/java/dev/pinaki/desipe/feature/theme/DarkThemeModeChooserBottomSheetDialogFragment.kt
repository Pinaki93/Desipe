package dev.pinaki.desipe.feature.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinaki.desipe.common.base.BaseBottomSheetDialogFragment
import dev.pinaki.desipe.databinding.ThemeChooserBottomSheetBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DarkThemeModeChooserBottomSheetDialogFragment :
    BaseBottomSheetDialogFragment<ThemeChooserBottomSheetBinding>() {

    private val viewModel: DarkThemeModeChooserViewModel by viewModel()

    private lateinit var adapter: DarkThemeModeChooserAdapter

    override fun initializeBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ThemeChooserBottomSheetBinding {
        return ThemeChooserBottomSheetBinding.inflate(inflater, parent, false)
    }

    override fun initializeView() {
        adapter = DarkThemeModeChooserAdapter()
        adapter.clickListener = {
            viewModel.onThemeChosen(it)
        }

        with(binding.rvItems) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DarkThemeModeChooserBottomSheetDialogFragment.adapter
        }
    }

    @Suppress("NestedLambdaShadowedImplicitParameter")
    override fun observeDataAndActions() {
        viewModel.modes.observe(this, Observer { showThemeList(it) })

        viewModel.dismiss.observe(this, Observer { dismissViewModel() })
    }

    private fun showThemeList(list: List<Int>) {
        adapter.submitList(list.map { getString(it) })
    }

    private fun dismissViewModel() {
        findNavController().navigateUp()
    }

    override fun makeTopCornersRounded() = true

}