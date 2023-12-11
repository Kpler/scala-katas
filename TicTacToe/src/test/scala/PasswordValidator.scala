class PasswordValidator() {
  def validatePassword(password: String) : Boolean = {
    if (password.length <= 8) {
      false
    }

    true
  }
}
