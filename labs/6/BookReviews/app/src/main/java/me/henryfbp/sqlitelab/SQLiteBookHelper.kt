package me.henryfbp.sqlitelab

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
    }


    // Get All Books
    // 1. build the query
    // 2. get reference to writable DB
    // 3. go over each row, build book and add it to list
    // Add book to books
    // return books
    fun getAll(): List<Book> {
        val books = LinkedList<Book>()
        val query = String.format("SELECT * FROM %s", TABLE_BOOKS)
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val book = Book()
                book.id = java.lang.Long.parseLong(cursor.getString(0))
                book.title = cursor.getString(1)
                book.author = cursor.getString(2)
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
        val CREATE_BOOK_TABLE = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT," +
                "%s TEXT )",
                TABLE_BOOKS, KEY_ID, KEY_TITLE, KEY_AUTHOR)

        // create books table
        db.execSQL(CREATE_BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older books table if existed
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_BOOKS))

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

        // 3. insert
        book.id = db.insert(TABLE_BOOKS, null, //nullColumnHack
                values) // key/value -> keys = column names/values
        // 4. Close dbase
        db.close()

        return book
    }

    // Updating single book
    fun update(book: Book): Long? {

        // 1. get reference to writable DB
        val db = this.writableDatabase

        // 2. create ContentValues to add key "column"/value
        val values = ContentValues()
        values.put("title", book.title) // get title
        values.put("author", book.author) // get author

        // 3. updating row
        val l = db.update(TABLE_BOOKS, //table
                values, // column/value
                String.format("%s = ?", KEY_ID), // selections
                arrayOf(book.id.toString())).toLong() //selection args
        // 4. close dbase
        db.close()
        Log.d("UpdateBook", book.toString() + "\n")

        return l
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

}
