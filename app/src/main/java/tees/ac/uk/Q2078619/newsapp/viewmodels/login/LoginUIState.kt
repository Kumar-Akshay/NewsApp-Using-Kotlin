package tees.ac.uk.Q2078619.newsapp.viewmodels.login


data class LoginUIState(
    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false

)