package lsvapp.kitsu.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_main.*
import lsvapp.kitsu.R
import lsvapp.kitsu.domain.entity.Anime
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()
//    private val viewBinding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MainState.Error -> textView2.text = "Error"
                is MainState.Loading ->  textView2.text = "Loading"
                is MainState.Content -> initContent(it.anime)
            }
        }
    }

    private fun initContent(anime: Anime){
        textView2.text = anime.canonicalTitle
    }
}