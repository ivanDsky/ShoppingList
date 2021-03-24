package ua.zloyhr.shoppinglist.ui.shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.zloyhr.shoppinglist.R
import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem
import ua.zloyhr.shoppinglist.databinding.ItemShoppingBinding
import kotlin.math.max

class ShoppingItemsAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemsAdapter.ShoppingItemsViewHolder>() {
    inner class ShoppingItemsViewHolder(private val binding: ItemShoppingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.apply {
                tvName.text = item.name
                tvAmount.text = item.amount.toString()

                ivDelete.setOnClickListener{
                    viewModel.delete(item)
                }

                ivPlus.setOnClickListener{
                    viewModel.upsert(item.copy(amount = item.amount + 1))
                }

                ivMinus.setOnClickListener{
                    viewModel.upsert(item.copy(amount = max(item.amount - 1,0)))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemsViewHolder {
        val binding =
            ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingItemsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}