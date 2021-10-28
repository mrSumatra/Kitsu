package lsvapp.kitsu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.setGraph(R.navigation.nav_main)
    }
}