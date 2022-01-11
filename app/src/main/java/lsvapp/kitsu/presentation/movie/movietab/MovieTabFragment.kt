package lsvapp.kitsu.presentation.movie.movietab

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentTabMovieBinding
import lsvapp.kitsu.domain.entity.dto.AnimeDto
import lsvapp.kitsu.presentation.maintab.MainTabFragmentDirections
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import lsvapp.kitsu.presentation.utils.widget.adapter.ContentViewerItem
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieTabFragment : Fragment(R.layout.fragment_tab_movie) {

    private val binding: FragmentTabMovieBinding by viewBinding()
    private val viewModel: MovieTabViewModel by viewModel()
    private val mainRouter: MainRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.animeState.collect { state ->
                binding.animeProgress.isVisible = state is MovieTabState.Loading
                binding.error.isVisible = state is MovieTabState.Error
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = (state as? MovieTabState.Error)?.message
                )

                binding.popularContent.isVisible = state is MovieTabState.Content

                if (state is MovieTabState.Content) {
                    initAnime(state.anime)
                }
            }
        }
    }

    private fun initAnime(anime: List<AnimeDto>) {

        val items = anime.filter {
            it.averageRating.toDouble() > SORT_RATING
        }.map {
            ContentViewerItem.Anime(anime = it) {
                openDetails()
            }
        }.plus(
            ContentViewerItem.AllMovie(
                title = getString(R.string.movie_all_movie_title)
            ) { openAnime() }
        )
        binding.popularContent.setContent(items)
    }

    private fun openAnime() {
        val navCommand = NavCommand.To(MainTabFragmentDirections.globalActionToAnimeFragment())
        mainRouter.onCommand(navCommand)
    }

    private fun openDetails() {

    }

    companion object {
        const val SORT_RATING: Double = 80.0
    }
}