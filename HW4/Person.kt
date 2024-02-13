package HW4
class Person(val name: String) {
    private val phones: MutableList<String> = mutableListOf()
    private val emails: MutableList<String> = mutableListOf()

    fun addPhone(phone: String) {
        phones.add(phone)
    }

    fun addEmail(email: String) {
        emails.add(email)
    }

    fun getPhones(): List<String> {
        return phones
    }

    fun getEmails(): List<String> {
        return emails
    }
}