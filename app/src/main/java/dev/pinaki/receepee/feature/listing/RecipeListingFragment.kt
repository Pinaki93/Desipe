package dev.pinaki.receepee.feature.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinaki.receepee.R
import dev.pinaki.receepee.common.base.BaseFragment
import dev.pinaki.receepee.databinding.DetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListingFragment : BaseFragment<DetailsFragmentBinding>() {

    private val recipeListingAdapter = RecipeListingAdapter()
    private val viewModel: RecipeListingViewModel by viewModel()

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailsFragmentBinding {
        return DetailsFragmentBinding.inflate(inflater, container, false)
    }

    override fun initializeView() {
        recipeListingAdapter.onClick = {
            viewModel.onRecipeItemClick(it)
        }

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeListingAdapter

            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun observeDataAndActions() {
        viewModel.allRecipes.observe(this, Observer { recipeListingAdapter.submitList(it) })
    }

    override fun getToolbarInstance(): Toolbar? {
        return binding.toolbar
    }

    override fun getToolbarTitle(): String? {
        return getString(R.string.all_recipes)
    }
}