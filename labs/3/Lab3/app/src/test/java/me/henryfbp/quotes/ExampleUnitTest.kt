package me.henryfbp.quotes

import android.graphics.Color
import me.henryfbp.library.HLib.mixColors
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
            /***
             * Can we import my crappy functions?
             */
    fun can_import_henrylib() {
        try {
            var c = mixColors(Color.BLACK, Color.WHITE, 1.0f)
        } catch (e: RuntimeException) {
            assertEquals(e.javaClass, RuntimeException().javaClass)
        }


    }
}
