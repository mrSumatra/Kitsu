package lsvapp.kitsu.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import lsvapp.kitsu.presentation.utils.navigation.NavCommand

fun NavController.applyNavCommand(command: NavCommand) {
    when (command) {
        is NavCommand.To -> navigate(command.directions)
        is NavCommand.Back -> navigateUp()
        is NavCommand.BackTo -> popBackStack(command.destinationId, false)
    }
}

fun Fragment.goBack() {
    activity?.onBackPressed()
}

fun Fragment.toShowError(text: CharSequence) =
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()

