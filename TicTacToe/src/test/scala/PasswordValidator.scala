class PasswordValidator() {
  def checkPasswordLength(password: String): Boolean =
    password.length <= 8

  def checkLowerCase(password: String): Boolean =
    password.toLowerCase().equals(password)

  def checkUpperCase(password: String): Boolean =
    password.toUpperCase().equals(password)

  def validatePassword(password: String) : Boolean = {
    if (checkPasswordLength(password)) {
      return false
    }

    if (checkLowerCase(password)) {
      return false
    }

    if (checkUpperCase(password)) {
      return false
    }

    true
  }
}
