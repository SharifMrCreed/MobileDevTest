package tech.bawano.mobiledevtest.repository

import tech.bawano.mobiledevtest.models.Product

/**
 * This class shall call the data sources and ensure availability of the data when needed.
 * the data is coming from the database in this case but we can implement caching here in case its
 * coming from a rest api and it needs to be stored locally. this helps that we don't have to change
 * much of the app if the datasource changes. we just need to make changes to this file only
 */
class Repository (private val productDao: ProductDao) {

    suspend fun insert(product: Product) = productDao.insert(product)
    suspend fun update(product: Product) = productDao.update(product)
    fun getProduct(id: Int) = productDao.getProduct(id)
    fun getProducts() = productDao.getAllProducts()


}