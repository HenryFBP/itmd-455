package me.henryfbp.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


class SQLiteBookHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    // Books table name
    private static final String TABLE_BOOKS = "books";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";

    public SQLiteBookHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT," +
                        "%s TEXT )",
                TABLE_BOOKS, KEY_ID, KEY_TITLE, KEY_AUTHOR);

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_BOOKS));

        // create fresh books table
        this.onCreate(db);
    }
    /*CRUD operations (create "add", read "get", update, delete) */

    public Book add(Book book) {
        Log.d("add", book.toString() + "\n");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor()); // get author

        // 3. insert
        book.setId(db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values)); // key/value -> keys = column names/values
        // 4. Close dbase
        db.close();

        return book;
    }

    // Get All Books
    public List<Book> getAll() {
        List<Book> books = new LinkedList<>();

        // 1. build the query
        String query = String.format("SELECT * FROM %s", TABLE_BOOKS);

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;

        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Long.parseLong(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        for (Book b : books) {
            Log.d("getAll()", b.toString());
        }

        return books; // return books
    }

    // Updating single book
    public Long update(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); // get title
        values.put("author", book.getAuthor()); // get author

        // 3. updating row
        Long l = (long) db.update(TABLE_BOOKS, //table
                values, // column/value
                String.format("%s = ?", KEY_ID), // selections
                new String[]{String.valueOf(book.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdateBook", book.toString() + "\n");

        return l;
    }

    // Deleting single book
    public Book delete(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS,
                String.format("%s = ?", KEY_ID),
                new String[]{String.valueOf(book.getId())});

        // 3. close
        db.close();

        Log.d("delete", book.toString() + "\n");

        return book;
    }

    /**
     * Delete all books.
     *
     * @return Books that were deleted.
     */
    public List<Book> deleteAll() {
        List<Book> books = getAll();

        for (Book book : books) {
            delete(book);
        }

        return books;
    }

    /**
     * Delete all books given as arguments.
     */
    public List<Book> deleteAll(Book... books) {

        for (Book book : books) {
            delete(book);
        }

        return Arrays.asList(books);
    }
}
