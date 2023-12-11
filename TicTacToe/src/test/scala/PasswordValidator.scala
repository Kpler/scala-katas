class PasswordValidator() {
  def validatePassword(password: String) : Boolean = {
    val result = isLessThan9(password).flatMap(
      doesNotContainUnderscore
    ).flatMap(doesNotContainLowerCase)
      .flatMap(isLessThan9)
      .flatMap(doesNotContainUpperCase)
      .flatMap(doesNotContainDigit)
    result match {
      case Some(_) => true
      case None => false
    }
  }

  private def doesNotContainUnderscore(password: String): Option[String] = {
    if(!password.contains("_")) {
      return None
    }
    Option(password)
  }

  private def isLessThan9(password: String): Option[String] = {
    if(password.length <= 8) {
      return None
    }
    Option(password)
  }

  private def doesNotContainUpperCase(password: String): Option[String] = {
    if(password.toLowerCase().equals(password)) {
      return None
    }
    Option(password)
  }

  private def doesNotContainLowerCase(password: String): Option[String] = {
    if(password.toUpperCase().equals(password)) {
      return None
    }
    Option(password)
  }

  private def doesNotContainDigit(password: String): Option[String] = {
    if(!password.matches(".*\\d+.*")) {
      return None
    }
    Option(password)
  }
}
