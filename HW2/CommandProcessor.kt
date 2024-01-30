package HW2
import HW2.Person

class CommandProcessor {
    private val contactMap = mutableMapOf<String, Person>()
    private var lastPerson: Person? = null

    fun readCommand(input: String): Command {
        val commandParts = input.split("\\s+".toRegex())

        return when (commandParts[0]) {
            "exit" -> ExitCommand()
            "help" -> HelpCommand()
            "add" -> {
                if (commandParts.size >= 4) {
                    val name = commandParts[1]
                    val dataType = commandParts[2]
                    val dataValue = commandParts.subList(3, commandParts.size).joinToString(" ")

                    when (dataType) {
                        "phone" -> AddPhoneCommand(name, dataValue)
                        "email" -> AddEmailCommand(name, dataValue)
                        else -> InvalidCommand()
                    }
                } else {
                    InvalidCommand()
                }
            }
            "show" -> ShowCommand()
            else -> InvalidCommand()
        }
    }

    fun processCommand(command: Command): Boolean {
        when (command) {
            is ExitCommand -> return false
            is HelpCommand -> command.displayHelp()
            is AddPhoneCommand -> {
                val contact = contactMap.getOrPut(command.name) { Person(command.name) }
                contact.phone = command.phoneNumber
                lastPerson = contact
                println("Phone number added for ${command.name}: ${command.phoneNumber}")
            }
            is AddEmailCommand -> {
                val contact = contactMap.getOrPut(command.name) { Person(command.name) }
                contact.email = command.emailAddress
                lastPerson = contact
                println("Email address added for ${command.name}: ${command.emailAddress}")
            }
            is ShowCommand -> {
                if (lastPerson != null) {
                    println(lastPerson)
                } else {
                    println("Not initialized")
                }
            }
            else -> println("Unknown command. Type 'help' for assistance.")
        }
        
        return true
    }

    fun displayHelp() {
        println("Available commands:")
        println("exit - close the program")
        println("help - display help information")
        println("add <Name> phone <Phone Number> - add phone number for a contact")
        println("add <Name> email <Email Address> - add email address for a contact")
        println("show - display last added contact")
    }
}
private fun isValidPhoneNumber(phone: String): Boolean {
    val regex = Regex("""^\+\d{10,}$""")
    return regex.matches(phone)
}

private fun isValidEmail(email: String): Boolean {
    val regex = Regex("""[a-zA-Z]+@[a-zA-Z]+\.[a-zA-Z]+""")
    return regex.matches(email)
}

private fun readlnOrNull(): String? {
    val input = readLine()
    return if (input.isNullOrBlank()) null else input
}
sealed class Command {
    abstract fun isValid(): Boolean
}
class ExitCommand : Command() {
    override fun isValid(): Boolean = true
    override fun toString(): String = "ExitCommand"
}
class HelpCommand : Command() {
    override fun isValid(): Boolean = true
    override fun toString(): String = "HelpCommand"
    fun displayHelp() {
        println("Available commands:")
        println("exit - close the program")
        println("help - display help information")
        println("add <Name> phone <Phone Number> - add phone number for a contact")
        println("add <Name> email <Email Address> - add email address for a contact")
        println("show - display last added contact")
    }
}
data class AddPhoneCommand(val name: String, val phoneNumber: String) : Command() {
    override fun isValid(): Boolean = isValidPhoneNumber(phoneNumber)
    override fun toString(): String = "AddPhoneCommand($name, $phoneNumber)"
}

data class AddEmailCommand(val name: String, val emailAddress: String) : Command() {
    override fun isValid(): Boolean = isValidEmail(emailAddress)
    override fun toString(): String = "AddEmailCommand($name, $emailAddress)"
}

class InvalidCommand : Command() {
    override fun isValid(): Boolean = false
    override fun toString(): String = "InvalidCommand"
}

class ShowCommand : Command() {
    override fun isValid(): Boolean = true
    override fun toString(): String = "ShowCommand"
}