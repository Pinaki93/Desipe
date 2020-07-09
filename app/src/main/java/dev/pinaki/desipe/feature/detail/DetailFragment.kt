package dev.pinaki.desipe.feature.detail

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import coil.ImageLoader
import coil.request.LoadRequest
import dev.pinaki.desipe.R
import dev.pinaki.desipe.common.base.BaseFragment
import dev.pinaki.desipe.databinding.DetailsFragmentBinding
import dev.pinaki.desipe.feature.detail.heading.recipe.RecipeHeadingAdapter
import dev.pinaki.desipe.feature.detail.ingredients.IngredientsAdapter
import dev.pinaki.desipe.feature.detail.steps.RecipeStepsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailFragment : BaseFragment<DetailsFragmentBinding>() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()

    private val recipeHeadingAdapter = RecipeHeadingAdapter()
    private val recipeStepAdapter = RecipeStepsAdapter()
    private val ingredientsAdapter = IngredientsAdapter()

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
                ingredientsAdapter,
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
        detailsViewModel.recipe.observe(this, Observer {
            if (it != null) {
                binding.imageViewHeader.load(it.image)

                val title = it.title
                recipeHeadingAdapter.headingAndDescription = Pair(title, it.subtitle)
                binding.collapsingToolbarHeader.title = title

                ingredientsAdapter.submitList(it.ingredients)
                recipeStepAdapter.submitList(it.steps)
            } else {
                Timber.tag(TAG)
                    .e(IllegalAccessException("Entered details screen with a null recipe"))
                findNavController().navigateUp()
            }
        })
    }

    override fun getToolbarInstance() = binding.toolbar

    override fun fragmentHasOptionsMenu() = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "DetailsFragment"
    }
}