package lsvapp.kitsu.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import lsvapp.kitsu.R
import lsvapp.kitsu.domain.entity.Anime
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedTabFragment : Fragment(R.layout.fragment_tab_feed) {

    private val viewModel: FeedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is FeedState.Error -> textView2.text = "Error"
                is FeedState.Loading -> textView2.text = "Loading"
                is FeedState.Content -> initContent(it.anime)
            }
        }
    }

    private fun initContent(anime: Anime) {
        textView2.text = anime.canonicalTitle
    }
}