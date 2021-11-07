package lsvapp.kitsu.presentation.feed.tab

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val viewTabModel: FeedViewTabModel by viewModel()
    private val mainRouter: MainRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
    }

    private fun initState() {
        viewTabModel.state.observe(viewLifecycleOwner) { state ->
            binding.loading.isVisible = state is FeedTabState.Loading

            if (state is FeedTabState.Content) {
                initContent(state.post)
            }
        }
    }

    private fun initContent(post: List<Post>) {
        val adapter = PostAdapter()
        adapter.items = post.map {
            PostAdapterItem(
                post = it,
                openProfile = { openProfile(it.author.id) },
                openPost = { openPostDetails(it.id) }
            )
        }
        binding.content.adapter = adapter
        binding.content.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun openProfile(profileId: Long) {
        val navCommand =
            NavCommand.To(MainTabFragmentDirections.globalActionToProfileDetails(profileId))
        mainRouter.onCommand(navCommand)
    }

    private fun openPostDetails(postId: Long) {
        val navCommand =
            NavCommand.To(MainTabFragmentDirections.globalActionToPostDetails(postId))
        mainRouter.onCommand(navCommand)
    }
}