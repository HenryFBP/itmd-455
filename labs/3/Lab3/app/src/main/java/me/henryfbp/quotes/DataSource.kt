package me.henryfbp.quotes

import android.util.Log
import java.util.*

class DataSource {

    var imageQuotePool: ArrayList<ImageQuote> = ArrayList()

    constructor() {

        Collections.addAll(imageQuotePool,
                ImageQuote(R.drawable.steve_1, R.drawable.steve_hd_1, R.string.quote_1_sj),
                ImageQuote(R.drawable.steve_2, R.drawable.steve_hd_2, R.string.quote_2_sj),
                ImageQuote(R.drawable.steve_3, R.drawable.steve_hd_3, R.string.quote_3_sj),
                ImageQuote(R.drawable.steve_4, R.drawable.steve_hd_4, R.string.quote_4_sj),
                ImageQuote(R.drawable.steve_5, R.drawable.steve_hd_5, R.string.quote_5_sj),
                ImageQuote(R.drawable.steve_6, R.drawable.steve_hd_6, R.string.quote_6_sj),
                ImageQuote(R.drawable.steve_7, R.drawable.steve_hd_7, R.string.quote_7_sj),
                ImageQuote(R.drawable.steve_8, R.drawable.steve_hd_8, R.string.quote_8_sj),
                ImageQuote(R.drawable.steve_9, R.drawable.steve_hd_9, R.string.quote_9_sj),
                ImageQuote(R.drawable.steve_10, R.drawable.apple_hd, R.string.quote_10_sj),

                ImageQuote(R.drawable.johnny_1, R.drawable.johnny_hd_1, R.string.quote_1_j),
                ImageQuote(R.drawable.arnold_1, R.drawable.arnold_hd_1, R.string.quote_1_a),
                ImageQuote(R.drawable.brooke_1, R.drawable.brooke_hd_1, R.string.quote_1_bs),
                ImageQuote(R.drawable.dan_1, R.drawable.dan_hd_1, R.string.quote_1_dq),
                ImageQuote(R.drawable.george_1, R.drawable.george_hd_1, R.string.quote_1_gg)
        )
        Log.i("test", imageQuotePool.size.toString())
    }

    fun getDataSourceLength(): Int {
        return imageQuotePool.size
    }


}