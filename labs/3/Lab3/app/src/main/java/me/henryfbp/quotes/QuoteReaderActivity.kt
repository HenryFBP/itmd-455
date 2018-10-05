package me.henryfbp.quotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView


class QuoteReaderActivity : AppCompatActivity() {

    class QuoteAdapter() : BaseAdapter() {

        private lateinit var mContext: Context
        private lateinit var mInflator: LayoutInflater
        private lateinit var mDataSource: DataSource

        constructor(c: Context) : this() {
            this.mContext = c
            this.mInflator = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            this.mDataSource = DataSource()

        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mDataSource.getDataSourceLength();
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var thumbnail: ImageView
            var quote: TextView
            var convertViewNew: View? = convertView

            if (convertViewNew == null) {
                convertViewNew = mInflator.inflate(R.layout.list_item_layout, parent, false)
            }

            var imageQuote = mDataSource.imageQuotePool.get(position)

            thumbnail = convertViewNew!!.findViewById(R.id.thumb)
            thumbnail.setImageResource(imageQuote.image)

            quote = convertViewNew.findViewById(R.id.text)
            quote.setText(imageQuote.quote)

            return convertViewNew!!
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_reader)

        var mListView: ListView = findViewById(R.id.quotes_list)
        mListView.setAdapter(QuoteAdapter(this))

        mListView.onItemClickListener = OnItemClickListener { adapterView, view, position, long ->
            val i = Intent(this@QuoteReaderActivity, QuoteDetail::class.java)
            i.putExtra("position", position)
            startActivity(i)
        }

    }


}
