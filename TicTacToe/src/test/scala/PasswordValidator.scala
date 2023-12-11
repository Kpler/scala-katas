class PasswordValidator() {
  private def isLongerThan8(password: String): Boolean =
    password.length <= 8

  private def containsUpperCase(password: String): Boolean =
    password.toLowerCase().equals(password)

  private def containsLowerCase(password: String): Boolean =
    password.toUpperCase().equals(password)

  def validatePassword(password: String) : Boolean = {
    if (isLongerThan8(password)) {
      return false
    }

    if (containsLowerCase(password)) {
      return false
    }

    if (containsUpperCase(password)) {
      return false
    }

    true
  }
}
