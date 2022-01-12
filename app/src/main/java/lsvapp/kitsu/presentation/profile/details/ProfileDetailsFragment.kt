package lsvapp.kitsu.presentation.profile.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentProfileBinding
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.presentation.profile.CurrentProfileState
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile) {

    private val args: ProfileDetailsFragmentArgs by navArgs()
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileDetailsViewModel by viewModel { parametersOf(args.profileId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it is ProfileDetailsState.Loading
            binding.error.isVisible = it is ProfileDetailsState.Error
            binding.error.setStubView(
                title = getString(R.string.command_error),
                desc = (it as? CurrentProfileState.Error)?.message
            )

            if (it is ProfileDetailsState.Content) {
                initContent(it.user)
            }
        }
    }

    private fun initContent(user: User) {
        binding.cover.load(user.coverImage?.original)
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
}