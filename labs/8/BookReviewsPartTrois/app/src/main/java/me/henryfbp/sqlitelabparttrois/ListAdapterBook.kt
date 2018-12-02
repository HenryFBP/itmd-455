package me.henryfbp.sqlitelabparttrois

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView

class ListAdapterBook(context: Context, resource: Int, items: List<Book>) : ArrayAdapter<Book>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var v = convertView

        if (v == null) {

            val vi: LayoutInflater = LayoutInflater.from(context)
            v = vi.inflate(R.layout.item_single_book, null)
        }

        val p = getItem(position)

        if (p != null) {

            val tt = v!!.findViewById<TextView>(R.id._id)
            val tt1 = v.findViewById<TextView>(R.id.title)
            val tt3 = v.findViewById<TextView>(R.id.author)
            val rb = v.findViewById<RatingBar>(R.id.rating)

            if (tt != null) {
                tt.text = p.id!!.toString()
            }

            if (tt1 != null) {
                tt1.text = p.title
            }

            if (tt3 != null) {
                tt3.text = p.author
            }

            if (rb != null) {
                rb.rating = p.rating
            }
        }

        return v!!
    }
}
