package lsvapp.kitsu.presentation.movie.anime

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentAnimeBinding
import lsvapp.kitsu.presentation.movie.anime.adapter.AnimeAdapter
import lsvapp.kitsu.presentation.utils.goBack
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimeFragment : Fragment(R.layout.fragment_anime) {

    private val binding: FragmentAnimeBinding by viewBinding()
    private val viewModel: AnimeViewModel by viewModel()
    private val adapter = AnimeAdapter()
    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        binding.recycler.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.loading.isVisible = loadState.source.refresh is LoadState.Loading
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initAnime()

        lifecycleScope.launchWhenResumed {
            viewModel.errorEvent.value?.let {
                binding.error.isVisible = true
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = it
                )
            }
        }
    }

    private fun initToolbar() {
        binding.toolbar.setBackNavigation { goBack() }
    }

    private fun initAnime() {
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        binding.recycler.adapter = adapter
        lifecycleScope.launchWhenResumed {
            viewModel.animePagerFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter.addLoadStateListener(loadStateListener)
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}