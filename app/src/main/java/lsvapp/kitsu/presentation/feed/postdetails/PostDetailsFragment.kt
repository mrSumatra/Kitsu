package lsvapp.kitsu.presentation.feed.postdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.flow.collectLatest
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentPostDetailsBinding
import lsvapp.kitsu.domain.entity.Comment
import lsvapp.kitsu.domain.entity.Post
import lsvapp.kitsu.presentation.feed.postdetails.adapter.CommentAdapter
import lsvapp.kitsu.presentation.feed.postdetails.adapter.CommentItem
import lsvapp.kitsu.presentation.maintab.MainTabFragmentDirections
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {

    private val args: PostDetailsFragmentArgs by navArgs()
    private val binding: FragmentPostDetailsBinding by viewBinding()
    private val viewModel: PostDetailsViewModel by viewModel { parametersOf(args.post.id) }
    private val mainRouter: MainRouter by inject()

    val adapter = CommentAdapter()
    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadState ->
        binding.recyclerComments.isVisible = loadState.source.refresh is LoadState.NotLoading
        binding.loading.isVisible = loadState.source.refresh is LoadState.Loading
        binding.errorStub.isVisible = loadState.source.refresh is LoadState.Error

        binding.noDataStub.isVisible =
            loadState.source.refresh is LoadState.NotLoading && adapter.itemCount == 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(args.post)
        initContent(args.post)
        initPageContent()

        binding.sendComment.setOnClickListener {
            sendComment()
        }
    }

    private fun initPageContent() {
        binding.recyclerComments.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerComments.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.commentPagerFlow.collectLatest { comment ->
                adapter.submitData(comment.map {
                    it.toAdapterItem()
                })
            }
        }

        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        adapter.addLoadStateListener(loadStateListener)
    }

    private fun initToolbar(post: Post) {
        binding.authorAvatar.load(post.author.avatar.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = post.author.name
        binding.postCreate.text = post.createdAt
    }

    private fun initContent(post: Post) {
        binding.contentText.text = post.content
        binding.like.text = post.postLikesCount.toString()
        binding.comments.text = post.commentsCount.toString()
    }

    private fun openProfile(profileId: Long) {
        val navCommand =
            NavCommand.To(MainTabFragmentDirections.globalActionToProfileDetails(profileId))
        mainRouter.onCommand(navCommand)
    }

    private fun Comment.toAdapterItem() = CommentItem(
        comment = this,
        openProfile = { openProfile(profileId = this.author.id) }
    )

    private fun sendComment() {}
}