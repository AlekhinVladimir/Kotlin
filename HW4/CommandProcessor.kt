package HW4
class CommandProcessor {
    private val phoneBook = PhoneBook()

    fun processCommand(command: String): Boolean {
        val parts = command.split(" ")
        val cmd = parts[0]

        return when (cmd) {
            "add" -> {
                val name = parts[1]
                phoneBook.addPerson(name)
                true
            }
            "phone" -> {
                val name = parts[1]
                val phone = parts[2]
                phoneBook.addPhone(name, phone)
                true
            }
            "email" -> {
                val name = parts[1]
                val email = parts[2]
                phoneBook.addEmail(name, email)
                true
            }
            "show" -> {
                val name = parts[1]
                phoneBook.show(name)
                true
            }
            "find" -> {
                val value = parts[1]
                phoneBook.find(value)
                true
            }
            "quit" -> false
            else -> {
                println("Invalid command.")
                true
            }
            "export" -> {
                val filePath = parts[1]
                val exportResult = phoneBook.exportToFile(filePath)
                if (exportResult) {
                    println("Data exported successfully.")
                } else {
                    println("Export failed.")
                }
                true
            }
        }
    }
}