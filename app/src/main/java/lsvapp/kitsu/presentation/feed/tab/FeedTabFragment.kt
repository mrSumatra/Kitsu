package lsvapp.kitsu.presentation.feed.tab

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
import lsvapp.kitsu.databinding.FragmentTabFeedBinding
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.presentation.feed.tab.adapter.PostAdapter
import lsvapp.kitsu.presentation.feed.tab.adapter.PostAdapterItem
import lsvapp.kitsu.presentation.maintab.MainTabFragmentDirections
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedTabFragment : Fragment(R.layout.fragment_tab_feed) {

    private val binding: FragmentTabFeedBinding by viewBinding()

    private val viewModel: FeedViewTabModel by viewModel()
    private val mainRouter: MainRouter by inject()
    private val adapter = PostAdapter()
    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        binding.content.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.loading.isVisible = loadState.source.refresh is LoadState.Loading
        binding.error.isVisible = loadState.source.refresh is LoadState.Error
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initContent()
    }

    private fun initContent() {
        binding.content.layoutManager = LinearLayoutManager(requireContext())
        binding.content.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.postPagerFlow.collectLatest { post ->
                adapter.submitData(post.map {
                    it.toPostAdapterItem() }
                )
            }
        }

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter.addLoadStateListener(loadStateListener)
    }

    private fun openProfile(profileId: Long) {
        val navCommand =
            NavCommand.To(MainTabFragmentDirections.globalActionToProfileDetails(profileId))
        mainRouter.onCommand(navCommand)
    }

    private fun openPostDetails(post: Post) {
        val navCommand = NavCommand.To(MainTabFragmentDirections.globalActionToPostDetails(post))
        mainRouter.onCommand(navCommand)
    }

    private fun Post.toPostAdapterItem() = PostAdapterItem(
        post = this,
        openProfile = { openProfile(this.author.id) },
        openPost = { openPostDetails(this) }
    )
}