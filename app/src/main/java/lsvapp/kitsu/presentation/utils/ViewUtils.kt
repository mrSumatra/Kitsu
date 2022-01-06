package lsvapp.kitsu.presentation.utils

import androidx.navigation.NavController
import lsvapp.kitsu.presentation.utils.navigation.NavCommand

fun NavController.applyNavCommand(command: NavCommand) {
    when (command) {
        is NavCommand.To -> navigate(command.directions)
        is NavCommand.Back -> navigateUp()
        is NavCommand.BackTo -> popBackStack(command.destinationId, false)
    }
}