package me.henryfbp.quotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class QuoteDetail : AppCompatActivity() {
    private lateinit var mImageView: ImageView
    private lateinit var mQuote: TextView
    private lateinit var mPosition: Integer
    private lateinit var mDataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_detail)

        val i = intent
        mPosition = i.getIntExtra("position", 0) as Integer

        mDataSource = DataSource()
        mImageView = findViewById<View>(R.id.image) as ImageView
        mQuote = findViewById<View>(R.id.quote) as TextView

        var imageQuote: ImageQuote = mDataSource.imageQuotePool.get(mPosition.toInt());

        mImageView.setImageResource(imageQuote.imageHD)

        mQuote.setText(imageQuote.quote)

    }
}
