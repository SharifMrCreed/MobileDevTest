package tech.bawano.mobiledevtest.ui.products

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tech.bawano.mobiledevtest.R
import tech.bawano.mobiledevtest.models.Product

class ProductsViewModel(app:Application) : AndroidViewModel(app) {

    val products: Flow<List<Product>>  = flow {
        val gson = Gson()
        val fileInputStream = app.applicationContext.resources.openRawResource(R.raw.offers)
        var jsonString = ""
        var character = fileInputStream.read()
        while (character != -1) {
            jsonString += character.toChar()
            character = fileInputStream.read()
        }
        val typeToken = object : TypeToken<List<Product>>() {}.type
        val products: List<Product> = gson.fromJson(jsonString, typeToken)
        Log.d("Parsed Products: ", products.toString())
        emit(products)
    }.flowOn(Dispatchers.IO)
}