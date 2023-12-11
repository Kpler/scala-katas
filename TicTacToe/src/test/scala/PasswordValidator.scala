class PasswordValidator() {
  def validatePassword(password: String) : Boolean = {
    if (isLessThan9(password)) {
      return false
    }

    if (doesNotContainLowerCase(password)) {
      return false
    }

    if (doesNotContainUpperCase(password)) {
      return false
    }

    if (doesNotContainDigit(password)) {
      return false
    }

    if (doesNotContainUnderscore(password)) {
      return false
    }

    true
  }

  private def doesNotContainUnderscore(password: String) = {
    !password.contains("_")
  }

  private def isLessThan9(password: String): Boolean =
    password.length <= 8

  private def doesNotContainUpperCase(password: String): Boolean =
    password.toLowerCase().equals(password)

  private def doesNotContainLowerCase(password: String): Boolean =
    password.toUpperCase().equals(password)

  private def doesNotContainDigit(password: String) = {
    !password.matches(".*\\d+.*")
  }
}
