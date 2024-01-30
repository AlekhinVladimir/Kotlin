package HW2
import HW2.CommandProcessor

fun main() {
    val commandProcessor = CommandProcessor()
    var continueProcessing = true
    
    while (continueProcessing) {
        print("Enter command: ")
        val input = readlnOrNull() ?: ""
        val command = commandProcessor.readCommand(input)
        
        println(command)
        
        if (!command.isValid()) {
            commandProcessor.displayHelp()
        } else {
            continueProcessing = commandProcessor.processCommand(command)
        }
    }
}