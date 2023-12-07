package com.tus.tusparking
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun onEmailChanged(email: String) {
        this.email = email
    }

    fun onPasswordChanged(password: String) {
        this.password = password
    }

    fun onSignInClicked() {

        viewModelScope.launch {
            kotlinx.coroutines.delay(1000)
            _uiState.value = HomeUiState(isSignInSuccess = true)
        }
    }

    fun onForgotPasswordClicked() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(500)
            _uiState.value = HomeUiState(navigateToForgotPassword = true)
        }
    }
}

data class HomeUiState(
    val isSignInSuccess: Boolean = false,
    val navigateToForgotPassword: Boolean = false
)

