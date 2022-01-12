package lsvapp.kitsu.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentProfileDetailsBinding
import lsvapp.kitsu.domain.entity.User
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentProfileTabFragment : Fragment(R.layout.fragment_profile_details) {

    private val binding: FragmentProfileDetailsBinding by viewBinding()
    private val viewModel: CurrentProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                binding.loading.isVisible = it is CurrentProfileState.Loading

                if (it is CurrentProfileState.Content) {
                    initContent(it.user)
                }
            }
        }
    }

    private fun initContent(user: User) {
        binding.textView3.text = user.name
    }
}