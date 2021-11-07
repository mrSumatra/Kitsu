package lsvapp.kitsu.presentation.utils.navigation

import androidx.navigation.NavDirections

sealed class NavCommand {

    data class To(
        val directions: NavDirections
    ) : NavCommand()

    object Back : NavCommand()

    data class BackTo(val destinationId: Int) : NavCommand()
}

