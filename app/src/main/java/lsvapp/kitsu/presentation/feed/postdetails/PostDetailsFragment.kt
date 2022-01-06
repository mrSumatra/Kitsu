package lsvapp.kitsu.presentation.feed.postdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentPostDetailsBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(args.post)
        initContent(args.post)
        initCommentsState()
    }

    private fun initCommentsState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loading.isVisible = state is PostDetailsState.Loading
            binding.error.isVisible = state is PostDetailsState.Error
            binding.error.text = (state as? PostDetailsState.Error)?.message

            if (state is PostDetailsState.Content) {
                initComments(state)
            }
        }
    }

    private fun initComments(state: PostDetailsState.Content) {
        val adapter = CommentAdapter()
        adapter.items = state.comments.map {
            CommentItem(
                comment = it,
                openProfile = { openProfile(it.author.id) }
            )
        }
        binding.recyclerComments.adapter = adapter
        binding.recyclerComments.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initToolbar(post: Post) {
        binding.authorAvatar.load(post.author.avatar.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = post.author.name
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
}