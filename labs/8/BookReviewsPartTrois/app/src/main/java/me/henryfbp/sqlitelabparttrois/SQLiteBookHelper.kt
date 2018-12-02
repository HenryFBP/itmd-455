package me.henryfbp.sqlitelabparttrois

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*


class SQLiteBookHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {

        // Database Version
        private const val DATABASE_VERSION = 2

        // Database Name
        private const val DATABASE_NAME = "BookDB"

        // Books table name
        private const val TABLE_BOOKS = "books"

        // Books Table Columns names
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_AUTHOR = "author"
        private const val KEY_RATING = "rating"
    }

    /**
     * Get Readable Database.
     */
    private val rDb: SQLiteDatabase
        get() {
            return readableDatabase
        }

    /**
     * Get Writable Database.
     */
    private val wDb: SQLiteDatabase
        get() {
            return writableDatabase
        }

    /**
     * Given an ID, return a book.
     */
    fun get(id: Long): Book {
        val query = "SELECT * FROM $TABLE_BOOKS WHERE $KEY_ID = $id"
        val cursor = this.rDb.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            return Book(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3))
        } else {
            throw Exception("ID not found.")
        }
    }

    // Get All Books
    // 1. build the query
    // 2. get reference to writable DB
    // 3. go over each row, build book and add it to list
    // Add book to books
    // return books
    fun getAll(): List<Book> {
        val books = LinkedList<Book>()
        val query = "SELECT * FROM $TABLE_BOOKS"
        val db = this.wDb
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val book = Book(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3))

                books.add(book)
            } while (cursor.moveToNext())
        }

        for (b in books) {
            Log.d("getAll()", b.toString())
        }

        return books
    }

    override fun onCreate(db: SQLiteDatabase) {
        // SQL statement to create book table
        val CREATE_BOOK_TABLE = """CREATE TABLE $TABLE_BOOKS (
            |$KEY_ID        INTEGER PRIMARY KEY AUTOINCREMENT,
            |$KEY_TITLE     TEXT,
            |$KEY_AUTHOR    TEXT,
            |$KEY_RATING    FLOAT)""".trimMargin()


        // create books table
        db.execSQL(CREATE_BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")

        // create fresh books table
        this.onCreate(db)
    }
    /*CRUD operations (create "add", read "get", update, delete) */

    fun add(book: Book): Book {
        Log.d("add", book.toString() + "\n")

        // 1. get reference to writable DB
        val db = this.writableDatabase

        // 2. create ContentValues to add key "column"/value
        val values = ContentValues()
        values.put(KEY_TITLE, book.title) // get title
        values.put(KEY_AUTHOR, book.author) // get author
        values.put(KEY_RATING, book.rating)

        // 3. insert
        book.id = db.insert(TABLE_BOOKS, null, //nullColumnHack
                values) // key/value -> keys = column names/values
        // 4. Close dbase
        db.close()

        return book
    }

    fun add(vararg books: Book) = books.forEach { book -> this.add(book) }

    fun add(books: List<Book>) = books.forEach { book -> this.add(book) }

    // Updating single book
    fun update(book: Book): Long {

        // 1. get reference to writable DB
        val db = this.writableDatabase

        // 2. create ContentValues to add key "column"/value
        val values = ContentValues()
        values.put(KEY_TITLE, book.title) // get title
        values.put(KEY_AUTHOR, book.author) // get author
        values.put(KEY_RATING, book.rating)

        // 3. updating row
        val l = db.update(TABLE_BOOKS, //table
                values, // column/value
                "$KEY_ID = ?", // selections
                arrayOf(book.id.toString())) //selection args
        // 4. close dbase
        db.close()
        Log.d("UpdateBook", book.toString() + "\n")

        return l.toLong()
    }

    fun update(oldbook: Book, newbook: Book): Book {


        Log.i(javaClass.simpleName, "Updating this old book to a new one:")
        Log.i(javaClass.simpleName, oldbook.toString())
        Log.i(javaClass.simpleName, newbook.toString())

        newbook.id = oldbook.id!!

        /*
        We can use the old book's ID to just cause the SQLHelper to update the newbook as if it were
        the old book.
        */
        update(newbook)

        return newbook
    }

    /***
     * If I must make this method signature, I will. But begrudgingly!
     * Step 6 from word doc.
     *
     * Ha! Take that, awesome T.A.s!
     */
    fun update(oldbook: Book, newTitle: String, newAuthor: String): Int {

        val newbook = Book(oldbook.id!!, newTitle, newAuthor, oldbook.rating)

        val updatedbook = update(oldbook, newbook).id!!

        return updatedbook.toInt()
    }

    // Deleting single book
    fun delete(book: Book): Book {

        // 1. get reference to writable DB
        val db = this.writableDatabase

        // 2. delete
        db.delete(TABLE_BOOKS,
                String.format("%s = ?", KEY_ID),
                arrayOf(book.id.toString()))

        // 3. close
        db.close()

        Log.d("delete", book.toString() + "\n")

        return book
    }

    /**
     * Delete all books.
     *
     * @return Books that were deleted.
     */
    fun deleteAll(): List<Book> {
        val books = getAll()

        for (book in books) {
            delete(book)
        }

        return books
    }

    /**
     * Delete all books given as arguments.
     */
    fun deleteAll(vararg books: Book): List<Book> {

        for (book in books) {
            delete(book)
        }

        return Arrays.asList(*books)
    }

    /**
     * How many books?
     */
    fun getCount(): Int {
        val query = "SELECT $KEY_ID FROM $TABLE_BOOKS"

        val cursor = rDb.rawQuery(query, null)

        return cursor.count
    }

    /**
     * Book(s) with the largest rating.
     *
     * Old SQL version.
     */
    @Deprecated("Newer and simpler version exists.")
    fun getRatingMaxSQL(): ArrayList<Book> {

        // All book IDs sorted by their rating.
        val query = """
                            |SELECT
                                |$KEY_ID
                            |FROM
                                |$TABLE_BOOKS
                            |ORDER BY
                                |$KEY_RATING DESC""".trimMargin()

        val books = ArrayList<Book>()

        val cursor = rDb.rawQuery(query, null)

        // If any books exist,
        if (cursor.moveToFirst()) {

            // Get the highest one.
            val highest_book = get(cursor.getLong(0))

            while (cursor.moveToNext()) {

                // Get the next one.
                val nextbook = get(cursor.getLong(0))

                // If it's at LEAST as large or larger,
                if (nextbook.rating >= highest_book.rating) {
                    // Add it.
                    books.add(nextbook)
                }

            }

        }

        // Return our array.
        return books
    }

    /**
     * Get most popular books.
     */
    fun getRatingMax(): List<Book> { //TODO this is broken.
        val all = getAll()

        // Get highest rated book.
        val largestBook = all.maxBy { it.rating }

        assert(largestBook!!.rating == 5f)

        // Get all books that are the same rating.
        return all.filter { it.rating >= largestBook.rating }
    }

    /**
     * Get least-popular books.
     */
    fun getRatingMin(): List<Book> {
        val all = getAll()

        // Get lowest rated book.
        val largestBook = all.minBy { it.rating }

        // Get all books that are the same rating.
        return all.filter { it.rating == largestBook!!.rating }
    }

    fun getTotal(): Int = getAll().size

    fun bookTitlesContaining(keyword: String): List<Book> {
        return getAll().filter { it.title.toLowerCase().contains(keyword) }
    }

}
