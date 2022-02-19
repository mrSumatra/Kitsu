package lsvapp.kitsu.presentation.manga

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentMangaTabBinding
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding

class MangaTabFragment : Fragment(R.layout.fragment_manga_tab) {

    private val binding: FragmentMangaTabBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}