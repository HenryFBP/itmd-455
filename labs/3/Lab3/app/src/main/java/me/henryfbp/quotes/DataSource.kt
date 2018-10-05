package me.henryfbp.quotes

import android.util.Log
import java.util.*

class DataSource {

    var imageQuotePool: ArrayList<ImageQuote> = ArrayList()

    constructor() {

        Collections.addAll(imageQuotePool,
                ImageQuote(R.drawable.steve_1, R.drawable.steve_hd_1, R.string.quote_1),
                ImageQuote(R.drawable.steve_2, R.drawable.steve_hd_2, R.string.quote_2),
                ImageQuote(R.drawable.steve_3, R.drawable.steve_hd_3, R.string.quote_3),
                ImageQuote(R.drawable.steve_4, R.drawable.steve_hd_4, R.string.quote_4),
                ImageQuote(R.drawable.steve_5, R.drawable.steve_hd_5, R.string.quote_5),
                ImageQuote(R.drawable.steve_6, R.drawable.steve_hd_6, R.string.quote_6),
                ImageQuote(R.drawable.steve_7, R.drawable.steve_hd_7, R.string.quote_7),
                ImageQuote(R.drawable.steve_8, R.drawable.steve_hd_8, R.string.quote_8),
                ImageQuote(R.drawable.steve_9, R.drawable.steve_hd_9, R.string.quote_9),
                ImageQuote(R.drawable.steve_10, R.drawable.apple_hd, R.string.quote_10))
        Log.i("test", imageQuotePool.size.toString())
    }

    fun getDataSourceLength(): Int {
        return imageQuotePool.size
    }


}