package ua.zloyhr.shoppinglist.ui.shopping

import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddDialog(item: ShoppingItem)
}