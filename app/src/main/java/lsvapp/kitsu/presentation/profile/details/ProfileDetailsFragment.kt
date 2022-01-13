package lsvapp.kitsu.presentation.profile.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.flow.collect
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

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
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
    }

    private fun initContent(user: User) {
        binding.cover.load(user.coverImage?.original)
        binding.avatar.load(user.avatar?.original) {
            transformations(CircleCropTransformation())
            error(R.drawable.ic_profile)
            fallback(R.drawable.ic_profile)
            placeholder(R.drawable.ic_profile)

        }
        binding.flexibleCollapsing.title =
            user.name ?: getString(R.string.profile_details_no_name_stub)
        binding.titleLocation.text =
            user.location ?: getString(R.string.profile_details_location_stub)
        binding.titleBirthDate.text =
            user.birthday ?: getString(R.string.profile_details_birth_date_stub)
        binding.titleGender.text = user.gender ?: getString(R.string.profile_details_gender_stub)
        binding.like.text = user.likesGivenCount
        binding.followers.text = user.followersCount
        binding.following.text = user.followingCount
        binding.about.text = user.about ?: getString(R.string.profile_details_about_stub)
    }
}