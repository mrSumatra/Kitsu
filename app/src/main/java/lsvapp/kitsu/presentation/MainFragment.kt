package lsvapp.kitsu.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import lsvapp.kitsu.R
import lsvapp.kitsu.domain.entity.Anime
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initState()
    }

    private fun initState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MainState.Error -> println("error")
                is MainState.Loading -> println("loading")
                is MainState.Content -> initContent(it.anime)
            }
        }
    }

    private fun initContent(anime: List<Anime>){
        println(anime)
    }
}