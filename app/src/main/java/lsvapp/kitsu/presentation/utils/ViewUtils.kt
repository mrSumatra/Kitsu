package lsvapp.kitsu.presentation.utils

import androidx.navigation.NavController
import lsvapp.kitsu.presentation.utils.navigation.NavCommand


//@JvmSynthetic
//inline fun ImageView.loadTitanFile(
//    link: String,
//    imageLoader: ImageLoader = Coil.imageLoader(context),
//    builder: ImageRequest.Builder.() -> Unit = {}
//): Disposable = loadAny(FilesApi.buildImageUri(uuid), imageLoader, builder)

fun NavController.applyNavCommand(command: NavCommand) {
    when (command) {
        is NavCommand.To -> navigate(command.directions)
        is NavCommand.Back -> navigateUp()
        is NavCommand.BackTo -> popBackStack(command.destinationId, false)
    }
}