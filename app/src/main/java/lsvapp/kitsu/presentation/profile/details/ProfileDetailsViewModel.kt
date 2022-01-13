package lsvapp.kitsu.presentation.profile.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.UserInteractor

class ProfileDetailsViewModel(
    val id: Long,
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileDetailsState>(ProfileDetailsState.Loading)
    val state: StateFlow<ProfileDetailsState> = _state

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _state.value = try {
                val user = userInteractor.getUserById(id)
                ProfileDetailsState.Content(user)
            } catch (e: Exception) {
                println("ERROR = ${e.message}")
                ProfileDetailsState.Error(e.message)
            }
        }
    }
}