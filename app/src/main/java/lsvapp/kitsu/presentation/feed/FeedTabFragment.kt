package lsvapp.kitsu.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentTabFeedBinding
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.presentation.feed.adapter.PostAdapter
import lsvapp.kitsu.presentation.feed.adapter.PostAdapterItem
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedTabFragment : Fragment(R.layout.fragment_tab_feed) {

    private val binding: FragmentTabFeedBinding by viewBinding()
    private val viewModel: FeedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loading.isVisible = state is FeedState.Loading

            if (state is FeedState.Content) {
                initContent(state.post)
            }
        }
    }

    private fun initContent(post: List<Post>) {
        val adapter = PostAdapter()
        adapter.items = post.map { PostAdapterItem(it) }
        binding.content.adapter = adapter
        binding.content.layoutManager = LinearLayoutManager(requireContext())
    }
}