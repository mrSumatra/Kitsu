package lsvapp.kitsu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.flow.collect
import lsvapp.kitsu.presentation.utils.applyNavCommand
import lsvapp.kitsu.presentation.utils.navigation.MainRouter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment).navController
    }

    private val mainRouter: MainRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRouter()
        navController.setGraph(R.navigation.nav_main)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun initRouter() {
        lifecycleScope.launchWhenStarted {
            mainRouter.commands.collect {
                navController.applyNavCommand(it)
            }
        }
    }
}