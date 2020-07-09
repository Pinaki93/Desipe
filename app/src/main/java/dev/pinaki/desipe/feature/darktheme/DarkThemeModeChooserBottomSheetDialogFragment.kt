package dev.pinaki.desipe.feature.darktheme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.map
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
        viewModel.modes
            .map { it.map { getString(it) } }
            .observe(this, Observer {
                adapter.submitList(it)
            })

        viewModel.dismiss.observe(this, Observer {
            findNavController().navigateUp()
        })
    }

    override fun makeTopCornersRounded() = true

}