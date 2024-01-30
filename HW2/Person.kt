package HW2
data class Person(val name: String) {
    var phone: String? = null
    var email: String? = null

    override fun toString(): String {
        return "Person(name=$name, phone=$phone, email=$email)"
    }
}