/*
 * MIT License
 *
 * Copyright (c) 2020 Pinaki Acharya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.pinaki.desipe.feature.listing

import android.view.*
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.base.BaseFragment
import dev.pinaki.desipe.common.util.*
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.databinding.ListingFragmentBinding

@AndroidEntryPoint
class RecipeListingFragment : BaseFragment<ListingFragmentBinding>(), View.OnClickListener {

    private val recipeListingAdapter = RecipeListingAdapter()
    private val viewModel: RecipeListingViewModel by viewModels()

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ListingFragmentBinding {
        return ListingFragmentBinding.inflate(inflater, container, false)
    }

    override fun initializeView() {
        setUpRecyclerView()
        binding.buttonRetry.setOnClickListener(this)
    }

    private fun setUpRecyclerView() {
        recipeListingAdapter.onClick = { recipe: Recipe, imageView: ImageView ->
            val imageUrl = recipe.image
            val extras = FragmentNavigatorExtras(imageView to imageUrl)
            val action =
                RecipeListingFragmentDirections.actionRecipeListingFragmentToDetailFragment(
                    recipe.id, imageUrl
                )

            findNavController().navigate(action, extras)
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

            // set up transition on the recyclerview
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                return@addOnPreDrawListener true
            }
        }
    }

    override fun observeDataAndActions() {
        with(viewModel) {
            allRecipes.observe(this@RecipeListingFragment, Observer { showRecipeList(it) })

            showLoading.observe(this@RecipeListingFragment, Observer { showLoading(it) })

            showOffline.observe(this@RecipeListingFragment, Observer { showOfflineView(it) })

            showError.observe(this@RecipeListingFragment, Observer { showError(it) })
        }
    }

    private fun showRecipeList(it: List<Recipe>) {
        recipeListingAdapter.submitList(it)
    }

    private fun showOfflineView(shouldShow: Boolean) {
        showDataLoadingView(shouldShow)
        if (shouldShow) {
            startLoopingAnimation(R.raw.no_internet_connection)

            with(binding) {
                tvHeadline.visible()
                tvHeadline.text = getString(R.string.title_not_connected_to_internet)

                tvContent.visible()
                tvContent.text = getString(R.string.msg_not_connected_to_internet)

                buttonRetry.visible()
            }
        }
    }

    private fun showLoading(shouldShow: Boolean) {
        showDataLoadingView(shouldShow)
        if (shouldShow) {
            startLoopingAnimation(R.raw.recipe_loading)

            with(binding) {
                tvHeadline.visible()
                tvHeadline.text = getString(R.string.loading_data)

                tvContent.gone()
            }
        }
    }

    private fun showError(shouldShow: Boolean) {
        showDataLoadingView(shouldShow)
        if (shouldShow) {
            startLoopingAnimation(R.raw.no_internet_connection)

            with(binding) {
                tvHeadline.visible()
                tvHeadline.text = getString(R.string.title_server_error)

                tvContent.visible()
                tvContent.text = getString(R.string.msg_error_occurred_while_loading_data)

                buttonRetry.visible()
            }
        }
    }

    private fun startLoopingAnimation(@RawRes resource: Int) {
        with(binding) {
            if (!animationView.isVisible())
                animationView.visible()

            animationView.startLoopingAnimation(resource)
        }
    }

    private fun showDataLoadingView(shouldShow: Boolean) {
        with(binding) {
            if (shouldShow) {
                if (!dataLoadingView.isVisible()) {
                    dataLoadingView.visible()
                    recyclerView.gone()
                    toolbar.gone()
                    buttonRetry.gone()
                }
            } else {
                if (!dataLoadingView.isGone()) {
                    dataLoadingView.gone()
                    recyclerView.visible()
                    toolbar.visible()
                }
            }
        }
    }

    override fun getToolbarInstance(): Toolbar? {
        return binding.toolbar
    }

    override fun getToolbarTitle(): String? {
        return getString(R.string.all_recipes)
    }

    override fun fragmentHasOptionsMenu() = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.listing_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_refrsh) {
            viewModel.syncRecipes()
        } else if (item.itemId == R.id.menu_item_change_theme) {
            showThemeChooser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showThemeChooser() {
        findNavController().navigate(
            RecipeListingFragmentDirections
                .actionRecipeListingFragmentToDarkThemeModeChooserBottomSheetDialogFragment()
        )
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_retry) {
            viewModel.syncRecipes()
        }
    }
}