package sp.bvantur.tasky.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sp.bvantur.tasky.core.domain.DispatcherProvider
import sp.bvantur.tasky.core.domain.ValidateEmailUseCase
import sp.bvantur.tasky.core.domain.ValidatePasswordUseCase
import sp.bvantur.tasky.core.presentation.SingleEventHandler
import sp.bvantur.tasky.core.presentation.SingleEventHandlerImpl
import sp.bvantur.tasky.core.presentation.ViewModelUserActionHandler
import sp.bvantur.tasky.core.presentation.ViewModelViewStateHandler
import sp.bvantur.tasky.core.presentation.ViewModelViewStateHandlerImpl
import sp.bvantur.tasky.login.domain.LoginRepository

class LoginViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginRepository: LoginRepository
) : ViewModel(),
    ViewModelUserActionHandler<LoginUserAction>,
    ViewModelViewStateHandler<LoginViewState> by ViewModelViewStateHandlerImpl(
        LoginViewState(),
        dispatcherProvider
    ),
    SingleEventHandler<LoginSingleEvent> by SingleEventHandlerImpl(dispatcherProvider) {
    override fun onUserAction(userAction: LoginUserAction) {
        when (userAction) {
            is LoginUserAction.EmailChanged -> onEmailChanged(userAction.value)
            is LoginUserAction.PasswordChanged -> onPasswordChanged(userAction.value)
            LoginUserAction.OnLogin -> onLogin()
            LoginUserAction.DismissErrorDialog -> onDismissErrorDialog()
        }
    }

    private fun onLogin() {
        viewModelScope.launch {
            val isEmailValid = validateEmailUseCase.invoke(viewStateFlow.value.email)
            val isPasswordValid = validateEmailUseCase.invoke(viewStateFlow.value.email)

            if (!isEmailValid || !isPasswordValid) {
                emitViewState { viewState ->
                    viewState.copy(
                        isEmailError = !isEmailValid,
                        isPasswordError = !isPasswordValid
                    )
                }
                return@launch
            }

            val result = loginRepository.login(
                viewStateFlow.value.email,
                viewStateFlow.value.password
            )

            if (result) {
                emitSingleEvent(LoginSingleEvent.OpenHome)
            } else {
                emitViewState { viewState ->
                    viewState.copy(
                        showErrorDialog = true
                    )
                }
            }
        }
    }

    private fun onEmailChanged(value: String) {
        viewModelScope.launch {
            val isValid = validateEmailUseCase.invoke(value)
            emitViewState { viewState ->
                viewState.copy(
                    isEmailError = !isValid,
                    email = value
                )
            }
        }
    }

    private fun onPasswordChanged(value: String) {
        viewModelScope.launch {
            val isValid = validatePasswordUseCase.invoke(value)
            emitViewState { viewState ->
                viewState.copy(
                    isPasswordError = !isValid,
                    password = value
                )
            }
        }
    }

    private fun onDismissErrorDialog() {
        viewModelScope.launch {
            emitViewState { viewState ->
                viewState.copy(showErrorDialog = false)
            }
        }
    }
}
