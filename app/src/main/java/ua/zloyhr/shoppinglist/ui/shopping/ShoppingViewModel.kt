package ua.zloyhr.shoppinglist.ui.shopping

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.zloyhr.shoppinglist.data.db.ShoppingDatabase
import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem
import ua.zloyhr.shoppinglist.data.repositories.ShoppingRepository

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}