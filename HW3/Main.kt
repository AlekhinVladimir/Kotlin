package HW3
fun main() {
    val commandProcessor = CommandProcessor()
    var running = true
    
    while (running) {
        print("Enter a command: ")
        val input = readlnOrNull()

        if (input != null) {
            val command = input.trim()
            running = commandProcessor.processCommand(command)
        }
    }
}