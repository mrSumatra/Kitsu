package lsvapp.kitsu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lsvapp.kitsu.presentation.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.navigation_container, MainFragment())
            .commit()
    }
}