package ua.zloyhr.shoppinglist.ui.shopping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import ua.zloyhr.shoppinglist.R
import ua.zloyhr.shoppinglist.data.db.entities.ShoppingItem
import ua.zloyhr.shoppinglist.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, val listener: AddDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            btnOk.setOnClickListener{
                val name = etName.text
                val value = etAmount.text

                if(name.isNotBlank() && value.isNotBlank()){
                    listener.onAddDialog(ShoppingItem(name.toString(),value.toString().toInt()))
                }else{
                    Toast.makeText(context,"Incorrect input data",Toast.LENGTH_SHORT).show()
                }
                dismiss()
            }

            btnCancel.setOnClickListener {
                cancel()
            }
        }
    }

}