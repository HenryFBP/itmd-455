package me.henryfbp.sqlitelab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteBookHelper db = new SQLiteBookHelper(this);

        Log.d(db.getClass().getSimpleName(), "Deleting all books.");
        db.deleteAll();

        /* CRUD Operations */

        Log.d(db.getClass().getSimpleName(), "Adding some cool books.");
        // add Books
        db.add(new Book("Android Studio Development Essentials", "Neil Smyth"));
        db.add(new Book("Beginning Android Application Development", "Wei-Meng Lee"));
        db.add(new Book("Programming Android", "Wallace Jackson"));
        db.add(new Book("Hello, Android", "Wallace Jackson"));
        // A test book.
        db.add(new Book("I am Lord of Potatoes", "god"));

        List<Book> list = db.getAll();

        Book b = list.get(list.size() - 1);
        b.setAuthor("Potato Lord");

        // Make the author "Potato Lord" for testing purposes.
        db.update(b);

        // Ensure it worked.
        if (!db.getAll().contains(new Book("I am Lord of Potatoes", "Potato Lord"))) {
            throw new AssertionError();
        }

        // Delete the last item. Potatoes are EVIL!
        db.delete(list.get(list.size() - 1));

        // Check that we have deleted it.
        db.getAll();

        //Test from Step 6:
        int x = 3;
        String s1 = "POTATO LORD RETURNS";
        String s2 = "SPUD MAN";

        int j = db.update(list.get(x), s1, s2);
        
        list = db.getAll();

        Book updated = list.get(x);

        if (j != updated.getId()) {
            throw new AssertionError(updated);
        }

        if (!updated.getTitle().equalsIgnoreCase(s1)) {
            throw new AssertionError(updated);
        }

        if (!updated.getAuthor().equalsIgnoreCase(s2)) {
            throw new AssertionError(updated);
        }


    }
}
