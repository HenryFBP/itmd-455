package me.henryfbp.sqlitelabparttrois

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var db: SQLiteBookHelper
    val listSpinnerChoices = listOf("Highest Rated", "Lowest Rated", "Record Count", "Titles containing 'Android'")

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, `length-osity`: Long) {

        val choice: String = (adapterView.adapter.getItem(position) as String).toLowerCase()

        when (choice) {
            "highest rated" -> {

                var bookList = db.getRatingMax()

                var message = "Got ${bookList.size} top books! Here they are:\n"

                for (book in bookList) {
                    message += "$book\n"
                }

                Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            }
            "lowest rated" -> {
                Toast.makeText(this, "Title :: ${db.getRatingMin()}", Toast.LENGTH_LONG).show()
            }
            "record count" -> {
                Toast.makeText(this, "Record Count :: ${db.getTotal()}", Toast.LENGTH_LONG).show()
            }
            "titles containing 'android'" -> {
                Toast.makeText(this, "Title :: ${db.getBooks()}", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQLiteBookHelper(this)

        val listViewBooks = findViewById<ListView>(R.id.listViewBooks)
        val spinnerChoices = findViewById<Spinner>(R.id.spinnerChoices)

        Log.d(db.javaClass.simpleName, "Deleting all books.")
        db.deleteAll()

        /* CRUD Operations */

        Log.d(db.javaClass.simpleName, "Adding some cool books.")

        db.add(Book("Android Studio Development Essentials", "Neil Smyth", 1f),
                Book("Beginning Android Application Development", "Wei-Meng Lee", 2f),
                Book("Programming Android", "Wallace Jackson", 3f),
                Book("I SHOULDN'T BE VISIBLE IF TESTING", "ASDF MAN", 5f),
                Book("Hello, Android", "Wallace Jackson", 5f),
                Book("Hello, Android 2", "Wallace Jackson 2", 5f)
        )


        // Populate spinner with choices.
        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listSpinnerChoices)
        spinnerChoices.adapter = arrayAdapter
        spinnerChoices.onItemSelectedListener = this // What happens when item is selected?

        // Populate UI with books.
        val customAdapter = ListAdapterBook(this, R.layout.item_single_book, db.getAll())
        listViewBooks.adapter = customAdapter


        //Tests.
        if (BuildConfig.DEBUG) {
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
            customAdapter.clear()
            customAdapter.addAll(db.getAll())
        }


    }
}
