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

package dev.pinaki.desipe.feature.detail

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequest
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.base.BaseFragment
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.databinding.DetailsFragmentBinding
import dev.pinaki.desipe.feature.detail.heading.recipe.RecipeHeadingAdapter
import dev.pinaki.desipe.feature.detail.heading.section.HeadingAdapter
import dev.pinaki.desipe.feature.detail.ingredients.IngredientsAdapter
import dev.pinaki.desipe.feature.detail.steps.RecipeStepsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailsFragmentBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()

    private val recipeHeadingAdapter = RecipeHeadingAdapter()
    private val recipeStepAdapter = RecipeStepsAdapter()
    private val recipeIngredientsAdapter = IngredientsAdapter()

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailsFragmentBinding {
        return DetailsFragmentBinding.inflate(inflater, container, false)
    }

    override fun initializeView() {
        setSharedElementTransitionOnEnter()
        postponeEnterTransition()
        setUpHeaderImage()
        setUpRecyclerView()
    }

    private fun setUpHeaderImage() {
        val imageUrl = detailFragmentArgs.imageurl
        binding.imageViewHeader.transitionName = imageUrl

        context?.run {
            val imageLoader = ImageLoader(this)
            val request = LoadRequest.Builder(this)
                .data(imageUrl)
                .target(onError = {
                    startPostponedEnterTransition()
                }, onSuccess = {
                    startPostponedEnterTransition()
                    binding.imageViewHeader.setImageDrawable(it)
                })
                .build()

            imageLoader.execute(request)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ConcatAdapter(
                recipeHeadingAdapter,
                HeadingAdapter(getString(R.string.ingredients)),
                recipeIngredientsAdapter,
                HeadingAdapter(getString(R.string.steps)),
                recipeStepAdapter
            )

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var totalScroll = 0
                var titleShown: Boolean? = null

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalScroll += dy
                    val showTitle = totalScroll > binding.toolbar.height
                    if (showTitle != titleShown) {
                        binding.collapsingToolbarHeader.post {
                            binding.collapsingToolbarHeader.isTitleEnabled = showTitle
                        }
                    }
                }
            })
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.change_image_transform)
    }

    override fun loadData() {
        detailsViewModel.load(detailFragmentArgs.recipeId)
    }

    override fun observeDataAndActions() {
        detailsViewModel.recipe.observe(this, Observer { showDetails(it) })
    }

    private fun showDetails(recipe: Recipe) {
        with(recipe) {
            binding.imageViewHeader.load(image)

            recipeHeadingAdapter.headingAndDescription = Pair(title, subtitle)
            binding.collapsingToolbarHeader.title = title

            recipeIngredientsAdapter.submitList(recipe.ingredients)
            recipeStepAdapter.submitList(recipe.steps)
        }
    }

    override fun getToolbarInstance() = binding.toolbar

    override fun fragmentHasOptionsMenu() = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPress()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onBackPress() {
        findNavController().navigateUp()
    }

    companion object {
        private const val TAG = "DetailsFragment"
    }
}