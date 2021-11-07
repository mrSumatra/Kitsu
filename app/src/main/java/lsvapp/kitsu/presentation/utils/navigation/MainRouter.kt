package lsvapp.kitsu.presentation.utils.navigation

import android.os.Bundle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.receiveAsFlow

class MainRouter {

    private val commandChannel: Channel<NavCommand> = Channel(Channel.RENDEZVOUS)
    private val restartAppFlowChannel: Channel<Bundle?> = Channel(Channel.RENDEZVOUS)

    val commands get() = commandChannel.receiveAsFlow()
    val restartAppFlow get() = restartAppFlowChannel.receiveAsFlow()

    fun onCommand(command: NavCommand) {
        commandChannel.sendBlocking(command)
    }

    fun back() = onCommand(NavCommand.Back)

    fun restartAppMainFlow(bundle: Bundle? = null) = restartAppFlowChannel.sendBlocking(bundle)

}
