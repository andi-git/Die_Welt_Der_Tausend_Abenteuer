package eu.ahammer.dieweltdertausendabenteuer.view.items

import android.content.Context
import android.util.Log
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.view.InputFieldDialog
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.items.ItemsViewModel
import kotlinx.coroutines.Runnable

class ItemsDialog(val itemsViewModel: ItemsViewModel) : InputFieldDialog() {

    fun showInsert(context: Context) {
        show(
            context = context,
            textTitle = "Gegenstand hinzufügen",
            textInput = null,
            textHint = "Gegenstand eingeben",
            textOk = "Gegenstand hinzugefügt",
            persist = { itemText ->
                Log.i("item", "insert item $itemText")
                itemsViewModel.addItem(Item(text = itemText))
            }
        )
    }

    fun showUpdate(context: Context, itemToChange: Item, afterItemChanged: Runnable) {
        show(
            context = context,
            textTitle = "Gegenstand ändern",
            textInput = itemToChange.text,
            textHint = null,
            textOk = "Gegenstand geändert",
            persist = { itemText ->
                Log.i("item", "update item from $itemToChange")
                itemToChange.text = itemText.toString()
                Log.i("item", "update item to $itemToChange")
                itemsViewModel.updateItem(itemToChange)
                afterItemChanged.run()
            }
        )
    }
}
