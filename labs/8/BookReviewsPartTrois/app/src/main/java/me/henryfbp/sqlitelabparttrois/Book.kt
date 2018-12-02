package me.henryfbp.sqlitelabparttrois

class Book() {

    constructor(id: Long, title: String, author: String, rating: Float) : this() {
        this.id = id
        this.title = title
        this.author = author
        this.rating = rating
    }

    constructor(title: String, author: String, rating: Float) : this() {
        this.title = title
        this.author = author
        this.rating = rating
    }

    var id: Long? = null

    lateinit var title: String
    lateinit var author: String
    var rating: Float = -1f


    override fun toString(): String {
        return String.format("%s [id=%02d, title=%s, author=%s, rating=%.2f]",
                javaClass.simpleName, id, title, author, rating)
    }

    fun equals(b: Book): Boolean? {
        return b.title.equals(title) &&
                b.author.equals(author) &&
                b.rating.equals(rating) &&
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
