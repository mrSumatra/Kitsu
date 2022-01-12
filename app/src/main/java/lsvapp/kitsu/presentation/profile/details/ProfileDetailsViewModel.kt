package lsvapp.kitsu.presentation.profile.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lsvapp.kitsu.domain.interactor.UserInteractor

class ProfileDetailsViewModel(
    val id: Long,
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _state = MutableLiveData<ProfileDetailsState>()
    val state: LiveData<ProfileDetailsState> = _state

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            _state.value = ProfileDetailsState.Loading
            try {
                val user = userInteractor.getUserById(id)
                _state.value = ProfileDetailsState.Content(user)
            } catch (e: Exception) {
                _state.value = ProfileDetailsState.Error(e.message)
            }
        }
    }
}