package lsvapp.kitsu.presentation.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentTabMovieBinding
import lsvapp.kitsu.domain.entity.Anime
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieTabFragment : Fragment(R.layout.fragment_tab_movie) {

    private val binding: FragmentTabMovieBinding by viewBinding()
    private val viewModel: MovieTabViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MovieTabState.Loading -> binding.textView.text = "Loading"
                is MovieTabState.Content -> initContent(it.anime)
                is MovieTabState.Error -> binding.textView.text = it.message
            }
        }
    }

    private fun initContent(anime: List<Anime>) {

    }
}