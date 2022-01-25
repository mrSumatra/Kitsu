package lsvapp.kitsu.presentation.movie.animelist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentAnimeListBinding
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.presentation.movie.animelist.adapter.AnimeListAdapter
import lsvapp.kitsu.presentation.movie.animelist.adapter.AnimeListAdapterItem
import lsvapp.kitsu.presentation.utils.goBack
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AnimeListFragment : Fragment(R.layout.fragment_anime_list) {

    private val args: AnimeListFragmentArgs by navArgs()
    private val binding: FragmentAnimeListBinding by viewBinding()
    private val listViewModel: AnimeListViewModel by viewModel { parametersOf(args.screenParams) }
    private val mainRouter: MainRouter by inject()

    private val adapter = AnimeListAdapter()
    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        binding.recycler.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.loading.isVisible = loadState.source.refresh is LoadState.Loading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initAnime()

        lifecycleScope.launchWhenResumed {
            listViewModel.errorEvent.value?.let {
                binding.error.isVisible = true
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = it
                )
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.title = args.screenParams.title ?: getString(R.string.movie_anime_title)
        binding.toolbar.setBackNavigation { goBack() }
    }

    private fun initAnime() {
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        binding.recycler.adapter = adapter
        lifecycleScope.launchWhenResumed {
            listViewModel.animeListPagerFlow.collectLatest { pagingData ->
                adapter.submitData(
                    pagingData.map { it.toAdapterItem() }
                )
            }
        }

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter.addLoadStateListener(loadStateListener)
    }

    private fun openAnimeDetails(anime: Anime) {
        val navCommand =
            NavCommand.To(AnimeListFragmentDirections.globalActionToAnimeDetailsFragment(anime))
        mainRouter.onCommand(navCommand)
    }

    private fun Anime.toAdapterItem() = AnimeListAdapterItem(
        anime = this
    ) { openAnimeDetails(this) }

    companion object {
        const val SPAN_COUNT = 2
    }
}