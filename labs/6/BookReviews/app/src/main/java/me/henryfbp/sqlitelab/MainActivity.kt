package me.henryfbp.sqlitelab

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = SQLiteBookHelper(this)

        Log.d(db.javaClass.simpleName, "Deleting all books.")
        db.deleteAll()

        /* CRUD Operations */

        Log.d(db.javaClass.simpleName, "Adding some cool books.")

        db.add(Book("Android Studio Development Essentials", "Neil Smyth"))
        db.add(Book("Beginning Android Application Development", "Wei-Meng Lee"))
        db.add(Book("Programming Android", "Wallace Jackson"))
        db.add(Book("Hello, Android", "Wallace Jackson"))

        // A test book.
        db.add(Book("I am Lord of Potatoes", "god"))

        // Make sure our book-updating works.

        var list = db.getAll()

        val b = list[list.size - 1]
        b.author = "Potato Lord"

        // Make the author "Potato Lord" for testing purposes.
        db.update(b)

        // Ensure it worked.
        assert(db.getAll().contains(Book("I am Lord of Potatoes", "Potato Lord")))

        // Delete the last item. Potatoes are EVIL!
        db.delete(list[list.size - 1])

        // Check that we have deleted it.
        db.getAll()

        //Test from Step 6.1:
        run {

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
        }


        // Test for step 6.2
        run {
            Log.i(db.javaClass.simpleName, "\n")

            assert(list.size == db.getCount()) { "${list.size} != ${db.getCount()}" }

            Log.i(db.javaClass.simpleName, "Step 6.2 worked! ${list.size} = ${db.getCount()}!")
        }

    }
}
