package lsvapp.kitsu.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

fun String.toHumanDataTime(): String {
    val localDateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_INSTANT)
    return localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
}

fun Fragment.toShowError(text: CharSequence) =
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()

