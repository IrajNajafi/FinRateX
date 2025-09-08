package com.irajnajafi1988gmail.finratex.ui.components.common.state
/**
 * Represents different types of errors that can occur in the app.
 */
sealed class UiStateError {

    /** Error when there is no internet connection */
    data object NoInternet : UiStateError()

    /**
     * Error returned from server
     * @param code HTTP status code
     * @param message Optional server message
     */
    data class Server(val code: Int, val message: String? = null) : UiStateError()

    /**
     * Unknown or unexpected error
     * @param throwable the caught exception
     */
    data class Unknown(val throwable: Throwable) : UiStateError()
}
