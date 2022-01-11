package lsvapp.kitsu.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import lsvapp.kitsu.presentation.utils.navigation.NavCommand
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
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

fun Instant.toLocalDateTime(zoneOffset: ZoneOffset = ZoneOffset.UTC): LocalDateTime =
    this.atZone(zoneOffset).toLocalDateTime()

fun String.toHumanDataTime(): String {
    val instant = Instant.parse(this)
    return instant.toLocalDateTime().toHumanDataTime()
}

fun LocalDateTime.toHumanDataTime(): String =
    this.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy "))


fun Fragment.toShowError(text: CharSequence) =
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()

