package eu.ahammer.dieweltdertausendabenteuer.viewmodel.item

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eu.ahammer.dieweltdertausendabenteuer.databinding.ItemsLayoutBinding
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item

class ItemAdapter(val context: Context, val list: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    private val itemDao = DWdtADatabase.get(context).itemDao()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemName.text = list[position].text
        holder.binding.itemDeleteButton.setOnClickListener{
            Log.i("item", "remove item ${list[position]}")
            itemDao.delete(list[position])
            notifyItemRemoved(position)
        }
        holder.itemView.setOnLongClickListener {
            val itemToChange = list[position]
            Log.i("item", "long click item $itemToChange")
            val dialog = AlertDialog.Builder(context)

            val input = EditText(context)
            input.text.append(list[position].text)
            input.inputType = InputType.TYPE_CLASS_TEXT
            dialog.setView(input)

            dialog.setPositiveButton("OK") { _, _ ->
                val itemText = input.text.toString()
                Log.i("item", "update item from $itemToChange")
                itemToChange.text = itemText.toString()
                Log.i("item", "update item to $itemToChange")

                DWdtADatabase.get(context).itemDao().update(itemToChange)
                notifyItemChanged(position)
                Toast.makeText(context, "Gegenstand geändert", Toast.LENGTH_SHORT)
                    .show()
            }
            dialog.setNegativeButton("Abbrechen") { _, _ ->
                Toast.makeText(context, "Abgebrochen", Toast.LENGTH_SHORT).show()
            }

            dialog.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}