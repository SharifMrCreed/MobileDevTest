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

    /** This view model is responsible for supplying the views with the necessary data they require.
     * Most of this data will come out of a repository
     * The repository needs a dao object that is provided by the database instance.
     * the database instance requires the application context for its instantiation
     * Dependency injection with hilt would make all this easier but would make the app abit complex
    */
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

    /**
     * This method gets the json data from the offers file provided as an input stream, then i
     * convert the bytes into characters and recreate the json data. It needs refactoring as
     * the encoding is not UTF-8 and some data is not well represented
     * it is also incredibly slow so im running it on the IO thread. There must be a better way to
     * do this but in the interest of time ill leave it at this. to combat some of this i save all
     * the products in a room database so that i don't have to call this method again
     */
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