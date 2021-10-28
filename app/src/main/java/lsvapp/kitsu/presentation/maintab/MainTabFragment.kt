package lsvapp.kitsu.presentation.maintab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.fragment_main_tab.*
import lsvapp.kitsu.R
import lsvapp.kitsu.databinding.FragmentMainTabBinding
import lsvapp.kitsu.presentation.utils.viewbinding.viewBinding

class MainTabFragment : Fragment(R.layout.fragment_main_tab) {
    private val binding: FragmentMainTabBinding by viewBinding()

    private val currentFragment get() = childFragmentManager.fragments.firstOrNull { !it.isHidden }
    private var currentTab: MainTabs? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        if (currentTab == null) {
            showTab(MainTabs.values().first())
        } else {
            showTab(currentTab!!)
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { selectedMenu ->
            val tabToShow = MainTabs.values()
                .find { it.menuId == selectedMenu.itemId }
                ?: error("Unknown itemId")

            showTab(tabToShow)
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun showTab(tab: MainTabs) {
        binding.bottomNavigationView.post {
            if (binding.bottomNavigationView.selectedItemId != tab.menuId && tab.menuId != null)
                binding.bottomNavigationView.selectedItemId = tab.menuId
        }

        val fragmentFromStack = childFragmentManager.findFragmentByTag(tab.tag.toString())

        if (fragmentFromStack != null)
            fragmentFromStack.arguments = null

        val fragmentToShow = fragmentFromStack ?: tab.newInstanceAction.invoke()
        if (fragmentToShow == currentFragment) return
        childFragmentManager.commit {
            childFragmentManager.fragments.forEach { hide(it) }
            if (!fragmentToShow.isAdded) add(
                R.id.container,
                fragmentToShow,
                tab.tag.toString()
            )
            currentTab = tab
            show(fragmentToShow)
        }
        childFragmentManager.executePendingTransactions()
    }
}