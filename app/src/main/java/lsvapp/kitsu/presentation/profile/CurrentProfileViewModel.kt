package lsvapp.kitsu.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.UserInteractor

class CurrentProfileViewModel(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _state = MutableStateFlow<CurrentProfileState>(CurrentProfileState.Loading)
    val state: StateFlow<CurrentProfileState> = _state

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _state.value = try {
                val user = userInteractor.getCurrentUser()
                CurrentProfileState.Content(user)
            } catch (e: Exception) {
                CurrentProfileState.Error(e.message)
            }
        }
    }
}