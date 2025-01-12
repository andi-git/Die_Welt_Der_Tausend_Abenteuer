package eu.ahammer.dieweltdertausendabenteuer.viewmodel.items

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.ahammer.dieweltdertausendabenteuer.databinding.ItemsLayoutBinding
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.view.items.ItemsCreateEditDialog
import eu.ahammer.dieweltdertausendabenteuer.view.items.ItemsDeleteDialog

class ItemsAdapter(val context: Context, val itemsViewModel: ItemsViewModel, val list: List<Item>) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemName.text = list[position].text
        holder.binding.itemDeleteButton.setOnClickListener {
            val itemToDelete = list[position]
            Log.i("item", "remove item $itemToDelete")
            ItemsDeleteDialog(itemsViewModel).showDelete(
                context = context,
                itemToDelete = itemToDelete,
                afterItemDeleted = {
                    notifyItemRemoved(position)
                })
        }
        holder.binding.itemEditButton.setOnClickListener {
            val itemToChange = list[position]
            Log.i("item", "remove hint $itemToChange")
            ItemsCreateEditDialog(itemsViewModel).showUpdate(
                context = context,
                itemToChange = itemToChange,
                afterItemChanged = {
                    notifyItemChanged(position)
                })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}