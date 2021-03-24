package ua.zloyhr.shoppinglist.ui.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import ua.zloyhr.shoppinglist.R
import ua.zloyhr.shoppinglist.data.db.ShoppingDatabase
import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem
import ua.zloyhr.shoppinglist.data.repositories.ShoppingRepository
import ua.zloyhr.shoppinglist.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein: Kodein by kodein()
    val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemsAdapter(listOf(ShoppingItem("Apples",4),ShoppingItem("Bananas",5)),viewModel)

        binding.apply {
            rvShoppingItems.layoutManager = LinearLayoutManager(this@ShoppingActivity)
            rvShoppingItems.adapter = adapter
            fabAddShoppingItem.setOnClickListener {
                AddShoppingItemDialog(this@ShoppingActivity,
                object : AddDialogListener{
                    override fun onAddDialog(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
            }
        }

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })


    }
}