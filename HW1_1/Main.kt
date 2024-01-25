fun main() {
    val commandProcessor = CommandProcessor()

    while (true) {
        print("Enter command: ")
        val input = readlnOrNull() ?: ""
        commandProcessor.processCommand(input)
        if (input == "exit") break
    }
}