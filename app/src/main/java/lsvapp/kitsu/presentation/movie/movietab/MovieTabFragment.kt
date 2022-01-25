package lsvapp.kitsu.presentation.movie.movietab

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentTabMovieBinding
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.presentation.maintab.MainTabFragmentDirections
import lsvapp.kitsu.presentation.movie.animelist.AnimeListFragmentDirections
import lsvapp.kitsu.presentation.movie.animelist.AnimeListParam
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

        initToolbar()
        initContent()
    }

    private fun initToolbar() {
        binding.toolbar.editRightEndIcon {
            setImageResource(R.drawable.ic_filter)
        }
    }

    private fun initContent() {
        lifecycleScope.launchWhenResumed {
            viewModel.animeState.collect { state ->
                binding.animeProgress.isVisible = state is MovieTabState.Loading
                binding.error.isVisible = state is MovieTabState.Error
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = (state as? MovieTabState.Error)?.message
                )

                binding.popularContent.isVisible = state is MovieTabState.Content
                binding.amazonContent.isVisible = state is MovieTabState.Content
                binding.actualContent.isVisible = state is MovieTabState.Content

                if (state is MovieTabState.Content) {
                    initAnime(state.anime)
                    initAnimeActual(state.actualAnime)
                    initAnimeByAmazon(state.animeByAmazon)
                }
            }
        }
    }

    private fun initAnime(anime: List<Anime>) {
        val items = anime.map {
            ContentViewerItem.Content(
                title = it.canonicalTitle,
                ageRating = it.ageRating.toString(),
                rating = it.averageRating,
                imageLink = it.posterImage.original
            ) {
                openDetails(it)
            }
        }.plus(
            ContentViewerItem.AllMovie(
                title = getString(R.string.movie_all_movie_title)
            ) { openAnime(AnimeListParam()) }
        )
        binding.popularContent.setContent(items)
    }

    private fun initAnimeActual(anime: List<Anime>) {
        val season = "2021"
        val items = anime.map {
            ContentViewerItem.Content(
                title = it.canonicalTitle,
                ageRating = it.ageRating.toString(),
                rating = it.averageRating,
                imageLink = it.posterImage.original
            ) { openDetails(it) }
        }.plus(
            ContentViewerItem.AllMovie(
                title = getString(R.string.movie_all_movie_title)
            ) { openAnime(AnimeListParam(seasonYear = season, title = "anime $season")) }
        )
        binding.actualContent.setContent(items)
    }

    private fun initAnimeByAmazon(anime: List<Anime>) {
        val items = anime.map {
            ContentViewerItem.Content(
                title = it.canonicalTitle,
                ageRating = it.ageRating.toString(),
                rating = it.averageRating,
                imageLink = it.posterImage.original
            ) { openDetails(it) }
        }.plus(
            ContentViewerItem.AllMovie(
                title = getString(R.string.movie_all_movie_title)
            ) { openAnime(AnimeListParam(streamers = "Amazon", title = "by Amazon")) }
        )
        binding.amazonContent.setContent(items)
    }

    private fun openAnime(param: AnimeListParam) {
        val navCommand = NavCommand.To(
            MainTabFragmentDirections.globalActionToAnimeFragment(
                param
            )
        )
        mainRouter.onCommand(navCommand)
    }

    private fun openDetails(animeDto: Anime) {
        val navCommand =
            NavCommand.To(AnimeListFragmentDirections.globalActionToAnimeDetailsFragment(animeDto))
        mainRouter.onCommand(navCommand)
    }
}