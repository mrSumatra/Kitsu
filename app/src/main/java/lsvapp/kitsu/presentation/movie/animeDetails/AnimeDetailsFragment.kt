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
import lsvapp.kitsu.domain.entity.dto.AnimeEpisodeDto
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import lsvapp.kitsu.presentation.utils.widget.adapter.ContentViewerItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AnimeDetailsFragment : Fragment(R.layout.fragment_anime_details) {

    private val args: AnimeDetailsFragmentArgs by navArgs()
    private val binding: FragmentAnimeDetailsBinding by viewBinding()
    private val viewModel: AnimeDetailsViewModel by viewModel() { parametersOf(args.anime.id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initContent()
        initEpisode()
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

    private fun initEpisode() {
        lifecycleScope.launchWhenResumed {
            viewModel.episodeState.collect { state ->
                binding.episodeLoading.isVisible = state is EpisodeState.Loading

                if (state is EpisodeState.Content) {
                    binding.contentViewer.setContent(
                        state.episodes.map { it.toContentItem() }
                    )
                }
            }
        }
    }

    fun AnimeEpisodeDto.toContentItem(): ContentViewerItem.Content = ContentViewerItem.Content(
        title = "${this.number} ${this.canonicalTitle}",
        imageLink = args.anime.posterImage.original,
        desc = getString(R.string.min_template, this.length)
    ) {}
}