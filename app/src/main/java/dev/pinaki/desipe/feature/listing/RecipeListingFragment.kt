package dev.pinaki.desipe.feature.listing

import android.view.*
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.base.BaseFragment
import dev.pinaki.desipe.common.util.gone
import dev.pinaki.desipe.common.util.isVisible
import dev.pinaki.desipe.common.util.startLoopingAnimation
import dev.pinaki.desipe.common.util.visible
import dev.pinaki.desipe.data.model.Recipe
import dev.pinaki.desipe.databinding.ListingFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListingFragment : BaseFragment<ListingFragmentBinding>(), View.OnClickListener {

    private val recipeListingAdapter = RecipeListingAdapter()
    private val viewModel: RecipeListingViewModel by viewModel()

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ListingFragmentBinding {
        return ListingFragmentBinding.inflate(inflater, container, false)
    }

    override fun initializeView() {
        recipeListingAdapter.onClick = { recipe: Recipe, imageView: ImageView ->
            val imageUrl = recipe.image
            val extras = FragmentNavigatorExtras(imageView to imageUrl)
            val action =
                RecipeListingFragmentDirections.actionRecipeListingFragmentToDetailFragment(
                    recipe.id, imageUrl
                )

            findNavController().navigate(action, extras)
        }

        binding.buttonRetry.setOnClickListener(this)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeListingAdapter

            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                return@addOnPreDrawListener true
            }
        }
    }

    override fun observeDataAndActions() {
        viewModel.allRecipes.observe(this, Observer { showRecipeList(it) })

        viewModel.showOffline.observe(this, Observer { showOfflineView(it) })

        viewModel.showLoading.observe(this, Observer { showLoading(it) })

        viewModel.showError.observe(this, Observer { showError(it) })
    }

    private fun showRecipeList(it: List<Recipe>) {
        recipeListingAdapter.submitList(it)
    }

    private fun showOfflineView(shouldShow: Boolean) {
        showDataLoadingView(true)
        if (shouldShow) {
            startLoopingAnimation(R.raw.no_internet_connection)

            binding.tvHeadline.visible()
            binding.tvHeadline.text = getString(R.string.title_not_connected_to_internet)

            binding.tvContent.visible()
            binding.tvContent.text = getString(R.string.msg_not_connected_to_internet)

            binding.buttonRetry.visible()
        }
    }

    private fun showLoading(shouldShow: Boolean) {
        showDataLoadingView(shouldShow)
        if (shouldShow) {
            startLoopingAnimation(R.raw.recipe_loading)

            binding.tvHeadline.visible()
            binding.tvHeadline.text = getString(R.string.loading_data)

            binding.tvContent.gone()
        }
    }

    private fun showError(shouldShow: Boolean) {
        showDataLoadingView(shouldShow)
        if (shouldShow) {
            startLoopingAnimation(R.raw.no_internet_connection)

            binding.tvHeadline.visible()
            binding.tvHeadline.text = getString(R.string.title_server_error)

            binding.tvContent.visible()
            binding.tvContent.text = getString(R.string.msg_error_occurred_while_loading_data)

            binding.buttonRetry.visible()
        }
    }

    private fun startLoopingAnimation(@RawRes resource: Int) {
        if (!binding.animationView.isVisible())
            binding.animationView.visible()

        binding.animationView.startLoopingAnimation(resource)
    }

    private fun showDataLoadingView(shouldShow: Boolean) {
        if (shouldShow) {
            binding.dataLoadingView.visible()
            binding.recyclerView.gone()
            binding.toolbar.gone()
            binding.buttonRetry.gone()
        } else {
            binding.dataLoadingView.gone()
            binding.recyclerView.visible()
            binding.toolbar.visible()
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
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_retry) {
            viewModel.syncRecipes()
        }
    }
}