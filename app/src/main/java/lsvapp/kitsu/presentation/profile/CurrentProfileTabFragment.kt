package lsvapp.kitsu.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentProfileBinding
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentProfileTabFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: CurrentProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                binding.loading.isVisible = it is CurrentProfileState.Loading
                binding.error.isVisible = it is CurrentProfileState.Error
                binding.error.setStubView(
                    title = getString(R.string.command_error),
                    desc = (it as? CurrentProfileState.Error)?.message
                )
                if (it is CurrentProfileState.Content) {
                    initContent(it.user)
                }
            }
        }
    }

    private fun initContent(user: User) {
        binding.cover.load(user.coverImage?.original ?: STUB_COVER_IMAGE_URL) {
        }
        binding.avatar.load(user.avatar?.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)
        }
        binding.flexibleCollapsing.title = user.name
        binding.titleLocation.text = user.location
        binding.titleBirthDate.text = user.birthday
        binding.titleGender.text = user.gender
        binding.like.text = user.likesGivenCount
        binding.followers.text = user.followersCount
        binding.following.text = user.followingCount
        binding.about.text = user.about
    }

    companion object {
        const val STUB_COVER_IMAGE_URL = "https://wallpaperaccess.com/full/2621363.jpg"
    }
}