package lsvapp.kitsu.presentation.feed.postdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentPostDetailsBinding
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {

    private val args: PostDetailsFragmentArgs by navArgs()
    private val binding: FragmentPostDetailsBinding by viewBinding()
    private val viewModel: PostDetailsViewModel by viewModel { parametersOf(args.postId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loading.isVisible = state is PostDetailsState.Loading

            if (state is PostDetailsState.Content) {
                initContent(state)
            }
        }
    }

    private fun initContent(content: PostDetailsState.Content) {
        binding.authorAvatar.load(content.post.author.avatar.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = content.post.author.name
        binding.postCreate.text = "TEST_CREATE DATE"
        binding.contentText.text = content.post.content
        binding.like.text = content.post.postLikesCount.toString()
        binding.comments.text = content.post.commentsCount.toString()
    }
}