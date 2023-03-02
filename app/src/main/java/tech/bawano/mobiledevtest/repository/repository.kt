package tech.bawano.mobiledevtest.repository

import tech.bawano.mobiledevtest.models.Product

class Repository (private val productDao: ProductDao) {

    suspend fun insert(product: Product) = productDao.insert(product)
    suspend fun update(product: Product) = productDao.update(product)
    fun getProduct(id: Int) = productDao.getProduct(id)
    fun getProducts() = productDao.getAllProducts()


}