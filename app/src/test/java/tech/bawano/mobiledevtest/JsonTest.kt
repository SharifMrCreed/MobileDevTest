package tech.bawano.mobiledevtest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import tech.bawano.mobiledevtest.models.Product
import java.io.BufferedReader
import java.io.InputStreamReader

class JsonTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testJsonParsing() {
        val gson = Gson()
        val inputStream = context.resources.openRawResource(R.raw.offers)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.readText()
        val typeToken = object : TypeToken<List<Product>>() {}.type
        val products: List<Product> = gson.fromJson(jsonString, typeToken)

        // Assert that the parsed list of products is not empty
        assertEquals(false, products.isEmpty())
    }
}
