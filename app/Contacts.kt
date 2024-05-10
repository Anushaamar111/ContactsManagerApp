//Each instance in this class creates a row in the datbase

data class Contacts(
  @Entity
  val  contacts_Number: Int,
    val contacts_name: String,
    val contacts_email: String
)
