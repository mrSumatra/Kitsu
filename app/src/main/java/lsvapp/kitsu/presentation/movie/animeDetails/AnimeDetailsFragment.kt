package lsvapp.kitsu.presentation.movie.animeDetails

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentAnimeDetailsBinding
import lsvapp.kitsu.domain.entity.AnimeEpisode
import lsvapp.kitsu.domain.entity.AnimeReaction
import lsvapp.kitsu.presentation.maintab.MainTabFragmentDirections
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import lsvapp.kitsu.presentation.utils.widget.adapter.ContentViewerItem
import lsvapp.kitsu.presentation.utils.widget.reaction.adapter.ReactionAdapterItem
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AnimeDetailsFragment : Fragment(R.layout.fragment_anime_details) {

    private val args: AnimeDetailsFragmentArgs by navArgs()
    private val binding: FragmentAnimeDetailsBinding by viewBinding()
    private val viewModel: AnimeDetailsViewModel by viewModel() { parametersOf(args.anime.id) }
    private val mainRouter: MainRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initContent()
        initInfo()
    }

    private fun initContent() {
        binding.image.load(args.anime.coverImage?.original) {
            transformations(BlurTransformation(requireContext()))
        }
        binding.imageCover.load(args.anime.posterImage.original) {
            RoundedCornersTransformation(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20f,
                    binding.root.context.resources.displayMetrics
                )
            )
        }
        binding.ageRating.text = args.anime.ageRating.toString()
        binding.rating.text = args.anime.averageRating
        binding.title.text = args.anime.canonicalTitle
        binding.userViewing.text = args.anime.userCount.toString()
        binding.time.text = args.anime.episodeLength.toString()
        binding.descContent.text = args.anime.description
    }

    private fun initInfo() {
        lifecycleScope.launchWhenResumed {
            viewModel.infoState.collect { state ->
                binding.episodeLoading.isVisible = state is EpisodeState.Loading

                if (state is EpisodeState.Content) {
                    initEpisodeAdapter(state.episodes)
                    initCategories(state.categories.map { it.title })
                    initReaction(state.reaction)
                }
            }
        }
    }

    private fun initEpisodeAdapter(episodes: List<AnimeEpisode>) {
        binding.contentViewer.setTitle(
            getString(
                R.string.anime_episode_total_template,
                args.anime.episodeCount
            )
        )
        binding.contentViewer.setContent(
            episodes.map { it.toContentItem() }
        )
    }

    fun AnimeEpisode.toContentItem(): ContentViewerItem.Content = ContentViewerItem.Content(
        title = "${this.number} ${this.canonicalTitle}",
        imageLink = args.anime.posterImage.original,
        desc = getString(R.string.min_template, this.length)
    ) { openEpisodeDetails(episodeId = this.id, animeId = args.anime.id) }

    private fun openEpisodeDetails(episodeId: Long, animeId: Long) {
        val navCommand = NavCommand.To(
            AnimeDetailsFragmentDirections.globalActionToAnimeEpisodeDetails(
                episodeId = episodeId,
                animeId = animeId
            )
        )
        mainRouter.onCommand(navCommand)
    }

    private fun initCategories(categories: List<String>) {
        binding.categories.setAdapterItem(categories)
    }

    private fun initReaction(reaction: List<AnimeReaction>) {
        val items = reaction.map {
            ReactionAdapterItem(
                reaction = it
            ) { openProfile(it.author.id) }
        }
        binding.reaction.setAdapterItem(items)
    }

    private fun openProfile(id: Long) {
        mainRouter.onCommand(NavCommand.To(MainTabFragmentDirections.globalActionToProfileDetails(id)))
    }
}