package HW4
import java.io.File
class PhoneBook {
    private val contacts: MutableMap<String, Person> = mutableMapOf()

    fun addPerson(name: String) {
        if (!contacts.containsKey(name)) {
            contacts[name] = Person(name)
        }
    }

    fun addPhone(name: String, phone: String) {
        val person = contacts[name]
        person?.addPhone(phone)
    }

    fun addEmail(name: String, email: String) {
        val person = contacts[name]
        person?.addEmail(email)
    }

    fun show(name: String) {
        val person = contacts[name]
        if (person != null) {
            val phones = person.getPhones()
            val emails = person.getEmails()

            println("Phones:")
            for (phone in phones) {
                println(phone)
            }

            println("Emails:")
            for (email in emails) {
                println(email)
            }
        } else {
            println("Person not found.")
        }
    }

    fun find(value: String) {
        val foundPeople = contacts.filter { 
            it.value.getPhones().contains(value) || it.value.getEmails().contains(value) 
        }
        if (foundPeople.isNotEmpty()) {
            println("People found:")
            for (person in foundPeople.keys) {
                println(person)
            }
        } else {
            println("No people found.")
        }
    }
    fun exportToFile(filePath: String): Boolean {
        val json = buildJson {
            array(contacts.values) {
                obj {
                    "name" to it.name
                    "phones" to it.getPhones()
                    "emails" to it.getEmails()
                }
            }
        }
        return try {
            File(filePath).writeText(json)
            true
        } catch (e: Exception) {
            false
        }
    }
}