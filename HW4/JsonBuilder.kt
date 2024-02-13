package HW4
class JsonBuilder(val result: StringBuilder = StringBuilder()) {
    fun array(items: Iterable<Any>, block: JsonBuilder.() -> Unit) {
        result.append("[")
        items.forEachIndexed { index, item ->
            if (index > 0) result.append(",")
            block(JsonBuilder(result))
        }
        result.append("]")
    }

    fun obj(block: JsonBuilder.() -> Unit) {
        result.append("{")
        block(JsonBuilder(result))
        result.append("}")
    }

    infix fun String.to(value: Any) {
        result.append("\"$this\":$value,")
    }

    infix fun String.to(block: JsonBuilder.() -> Unit) {
        result.append("\"$this\":")
        block(JsonBuilder(result))
        result.append(",")
    }
}

fun buildJson(block: JsonBuilder.() -> Unit): String {
    val builder = JsonBuilder()
    builder.block()
    return builder.result.toString().removeSuffix(",")
}