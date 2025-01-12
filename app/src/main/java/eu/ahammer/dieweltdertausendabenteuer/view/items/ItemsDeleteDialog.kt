package eu.ahammer.dieweltdertausendabenteuer.view.items

import android.content.Context
import android.util.Log
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.view.YesNoDialog
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.items.ItemsViewModel
import kotlinx.coroutines.Runnable

class ItemsDeleteDialog(val itemsViewModel: ItemsViewModel) : YesNoDialog() {

    fun showDelete(context: Context, itemToDelete: Item, afterItemDeleted: Runnable) {
        show(
            context = context,
            textTitle = "Gegenstand '${itemToDelete.text}' löschen?",
            textOk = "Gegenstand ${itemToDelete.text} gelöscht",
            textButtonOk = "Gegenstand löschen",
            perform = {
                Log.i("item", "delete item $itemToDelete")
                itemsViewModel.deleteItem(itemToDelete)
                afterItemDeleted.run()
            }
        )
    }
}
