package ua.zloyhr.shoppinglist.data.repositories

import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem
import ua.zloyhr.shoppinglist.data.db.ShoppingDatabase

class ShoppingRepository(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}