package me.henryfbp.sqlitelab

class Book() {

    constructor(id: Long, title: String, author: String) : this() {
        this.id = id
        this.title = title
        this.author = author
    }

    constructor(title: String, author: String) : this() {
        this.title = title
        this.author = author
    }

    var id: Long? = null

    lateinit var title: String

    lateinit var author: String


    override fun toString(): String {
        return String.format("%s [id=%02d, title=%s, author=%s]",
                javaClass.simpleName, id, title, author)
    }

    fun equals(b: Book): Boolean? {
        return b.title.equals(title) &&
                b.author.equals(author) &&
                b.id?.equals(id) ?: true
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Book -> {
                other.equals(this)!!
            }
            else -> {
                other == this
            }
        }
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }
}
