package lsvapp.kitsu.presentation.feed.postcreate

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentPostCreateBinding
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.presentation.utils.goBack
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostCreateFragment : Fragment(R.layout.fragment_post_create) {

    private val binding: FragmentPostCreateBinding by viewBinding()
    private val viewModel: PostCreateViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenResumed {
            viewModel.createPostState.collect {
                binding.loading.isVisible = it is PostCreateState.LoadingProfile
                binding.loadingPost.isVisible = it is PostCreateState.LoadingPost
                binding.error.isVisible = it is PostCreateState.Error
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = (it as? PostCreateState.Error)?.message
                )

                if (it is PostCreateState.CurrentProfile) {
                    initToolbar(it.user)
                }

                if (it is PostCreateState.sendPostSuccessful) {
                    goBack()
                }
            }
        }
    }

    private fun initToolbar(user: User) {
        binding.clearText.setOnClickListener {
            goBack()
        }
        binding.sendPost.setOnClickListener {
            sendPost()
        }

        binding.authorAvatar.load(user.avatar?.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.authorName.text = user.name
    }

    private fun sendPost() {
        viewModel.sendPost(binding.contentText.text.toString())
    }
}