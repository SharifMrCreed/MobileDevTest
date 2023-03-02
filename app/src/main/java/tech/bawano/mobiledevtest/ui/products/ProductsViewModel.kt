package tech.bawano.mobiledevtest.ui.products

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import tech.bawano.mobiledevtest.R
import tech.bawano.mobiledevtest.models.Product
import tech.bawano.mobiledevtest.repository.ProductDatabase
import tech.bawano.mobiledevtest.repository.Repository

class ProductsViewModel(val app:Application) : AndroidViewModel(app) {

    private val repository:Repository

    init {
        val productDatabase = ProductDatabase.getInstance(app)
        repository = Repository(productDatabase.productDao())
    }

    suspend fun getAllProducts() = repository.getProducts()

    fun update(product: Product) = viewModelScope.launch {
        repository.update(product)
    }

    suspend fun getProduct(id:Int) = repository.getProduct(id)

    fun insertProducts(){
        viewModelScope.launch(Dispatchers.IO) {
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
            products.forEach {
                repository.insert(it)
            }
        }
    }

}