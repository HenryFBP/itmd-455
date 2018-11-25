package me.henryfbp.sqlitelabpartdeux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = SQLiteBookHelper(this)

        Log.d(db.javaClass.simpleName, "Deleting all books.")
        db.deleteAll()

        /* CRUD Operations */

        Log.d(db.javaClass.simpleName, "Adding some cool books.")

        db.add(Book("Android Studio Development Essentials", "Neil Smyth", 1f))
        db.add(Book("Beginning Android Application Development", "Wei-Meng Lee", 2f))
        db.add(Book("Programming Android", "Wallace Jackson", 3f))
        db.add(Book("Hello, Android", "Wallace Jackson", 4f))

        // Populate UI with books.
        val listViewBooks = findViewById<ListView>(R.id.listViewBooks)
        val customAdapter = ListAdapterBook(this, R.layout.item_single_book, db.getAll())
        listViewBooks.adapter = customAdapter


        //Tests.
        run {
            // A test book.
            db.add(Book("I am Lord of Potatoes", "god", 5f))

            // Make sure our book-updating works.

            val list = db.getAll()

            val b = list[list.size - 1]
            b.author = "Potato Lord"

            // Make the author "Potato Lord" for testing purposes.
            db.update(b)

            // Ensure it worked.
            assert(db.getAll().contains(Book("I am Lord of Potatoes", "Potato Lord", 5f)))

            // Delete the last item. Potatoes are EVIL!
            db.delete(list[list.size - 1])

            // Check that we have deleted it.
            db.getAll()
        }.also {

            //Test from Step 6.1:
            var list = db.getAll()

            val x = 3
            val s1 = "POTATO LORD RETURNS"
            val s2 = "SPUD MAN"

            // Update book.
            val j = db.update(list[x], s1, s2)

            // Get updated book.
            list = db.getAll()
            val updated = list[x]

            assert(j.toLong() == updated.id) { updated }
            assert(updated.title.equals(s1, ignoreCase = true)) { updated }
            assert(updated.author.equals(s2, ignoreCase = true)) { updated }

            Log.i(db.javaClass.simpleName, "Step 6.1 worked!")


            // Test for step 6.2
            run {
                Log.i(db.javaClass.simpleName, "\n")

                assert(list.size == db.getCount()) { "${list.size} != ${db.getCount()}" }

                Log.i(db.javaClass.simpleName, "Step 6.2 worked! ${list.size} = ${db.getCount()}!")
            }

        }


    }
}
