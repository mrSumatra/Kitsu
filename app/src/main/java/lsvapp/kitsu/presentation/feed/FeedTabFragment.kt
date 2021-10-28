package lsvapp.kitsu.presentation.feed

import android.os.Bundle
import android.view.View
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
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is FeedState.Loading -> println("==== LOADING")
                is FeedState.Content -> initContent(it.posts)
                is FeedState.Error -> println("==== ERRORR = ${it.message}")
            }
        }
    }

    private fun initContent(posts: List<Post>) {
        val adapter = PostAdapter()
        adapter.items = posts.map { PostAdapterItem(it) }
        binding.content.adapter = adapter
        binding.content.layoutManager = LinearLayoutManager(requireContext())
    }
}