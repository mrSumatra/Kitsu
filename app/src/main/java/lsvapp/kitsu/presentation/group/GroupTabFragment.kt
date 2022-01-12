package lsvapp.kitsu.presentation.group

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentTabGroupBinding
import lsvapp.kitsu.domain.entity.Groupe
import lsvapp.kitsu.presentation.group.adapter.GroupeTabAdapter
import lsvapp.kitsu.presentation.group.adapter.GroupeTabAdapterItem
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupTabFragment : Fragment(R.layout.fragment_tab_group) {

    private val binding: FragmentTabGroupBinding by viewBinding()
    private val viewModel: GroupeTabViewModel by viewModel()

    private val adapter = GroupeTabAdapter()
    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        binding.content.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.loading.isVisible = loadState.source.refresh is LoadState.Loading
        binding.error.isVisible = loadState.source.refresh is LoadState.Error
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        lifecycleScope.launchWhenResumed {
            initContent()
        }
    }

    private fun initToolbar() {
        binding.toolbar.editRightEndIcon {
            setImageResource(R.drawable.ic_filter)
        }
    }

    private fun initContent() {
        binding.content.layoutManager = LinearLayoutManager(requireContext())
        binding.content.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.groupesPagerFlow.collectLatest { gropes ->
                adapter.submitData(gropes.map {
                    it.toAdapterItem()
                })
            }
        }

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter.addLoadStateListener(loadStateListener)
    }

    fun Groupe.toAdapterItem() = GroupeTabAdapterItem(
        groupe = this
    ) {}
}