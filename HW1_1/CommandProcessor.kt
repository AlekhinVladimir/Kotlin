import java.sql.DriverManager.println

class CommandProcessor {
    private val contactMap = mutableMapOf<String, Contact>()

    fun processCommand(input: String) {
        val commandParts = input.split("\\s+".toRegex())

        when (commandParts[0]) {
            "exit" -> return
            "help" -> displayHelp()
            "add" -> addContact(commandParts)
            else -> println("Unknown command. Type 'help' for assistance.")
        }
    }

    private fun addContact(commandParts: List<String>) {
        val name = commandParts[1]
        val contact = contactMap.getOrPut(name) { Contact(name) }

        when (commandParts[2]) {
            "phone" -> {
                val phone = commandParts[3]
                if (isValidPhone(phone)) {
                    contact.phone = phone
                    println("Phone number added for $name: $phone")
                } else {
                    println("Invalid phone number format. Please use format: +1234567890")
                }
            }
            "email" -> {
                val email = commandParts[3]
                if (isValidEmail(email)) {
                    contact.email = email
                    println("Email address added for $name: $email")
                } else {
                    println("Invalid email address format.")
                }
            }
            else -> println("Invalid command format. Type 'help' for assistance.")
        }
    }

    private fun isValidPhone(phone: String): Boolean {
        val regex = Regex("^\\+\\d{10,}$")
        return regex.matches(phone)
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = Regex("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+")
        return regex.matches(email)
    }

    private fun displayHelp() {
        println("Available commands:")
        println("exit - close the program")
        println("help - display help information")
        println("add <Name> phone <Phone Number> - add phone number for a contact")
        println("add <Name> email <Email Address> - add email address for a contact")
    }
}