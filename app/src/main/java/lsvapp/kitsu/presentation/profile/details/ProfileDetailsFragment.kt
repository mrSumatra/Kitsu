package lsvapp.kitsu.presentation.profile.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentProfileDetailsBinding
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileDetailsFragment : Fragment(R.layout.fragment_profile_details) {

    private val args: ProfileDetailsFragmentArgs by navArgs()
    private val binding: FragmentProfileDetailsBinding by viewBinding()
    private val viewModel: ProfileDetailsViewModel by viewModel { parametersOf(args.profileId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it is ProfileDetailsState.Loading

            if (it is ProfileDetailsState.Content) {
                initContent(it)
            }
        }
    }

    private fun initContent(content: ProfileDetailsState.Content) {
        binding.textView3.text = content.user.name
    }
}