package me.henryfbp.sqlitelabpartdeux;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ListAdapterBook extends ArrayAdapter<Book> {

    private List<Book> items;

    public ListAdapterBook(Context context, int resource, List<Book> items) {
        super(context, resource, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, parent);
        }

        Book p = getItem(position);

        if (p != null) {

            TextView tt = v.findViewById(R.id._id);
            TextView tt1 = v.findViewById(R.id.title);
            TextView tt3 = v.findViewById(R.id.author);
            RatingBar rb = v.findViewById(R.id.rating);

            if (tt != null) {
                tt.setText(p.getId().toString());
            }

            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }

            if (tt3 != null) {
                tt3.setText(p.getAuthor());
            }

            if (rb != null) {
                rb.setRating(p.getRating());
            }
        }
        return v;
    }
}
