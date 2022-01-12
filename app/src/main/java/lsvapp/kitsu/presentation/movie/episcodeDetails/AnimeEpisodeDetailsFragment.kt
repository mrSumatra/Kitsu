package lsvapp.kitsu.presentation.movie.episcodeDetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentAnimeEpisodeDetailsBinding
import lsvapp.kitsu.presentation.utils.goBack
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AnimeEpisodeDetailsFragment : Fragment(R.layout.fragment_anime_episode_details) {

    private val args: AnimeEpisodeDetailsFragmentArgs by navArgs()
    private val binding: FragmentAnimeEpisodeDetailsBinding by viewBinding()
    private val viewModel: AnimeEpisodeViewModel by viewModel {
        parametersOf(args.animeId, args.episodeId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                binding.loading.isVisible = state is EpisodeDetailsState.Loading
                binding.error.isVisible = state is EpisodeDetailsState.Error
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = (state as? EpisodeDetailsState.Error)?.message
                )

                if (state is EpisodeDetailsState.Content) {
                    initContent(state)
                }
            }
        }
    }

    private fun initContent(state: EpisodeDetailsState.Content) {
        val title = "${state.episode.number} ${state.episode.canonicalTitle} "
        initToolbar(title)
        binding.image.load(state.imageLink)
        binding.time.text = getString(R.string.min_template, state.episode.length)
        binding.descContent.text = state.episode.description
    }

    private fun initToolbar(title: String?) {
        binding.toolbar.title = title ?: "Title"
        binding.toolbar.setBackNavigation {
            goBack()
        }
    }
}