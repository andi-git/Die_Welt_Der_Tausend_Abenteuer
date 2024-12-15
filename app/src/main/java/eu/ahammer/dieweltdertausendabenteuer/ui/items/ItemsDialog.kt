package eu.ahammer.dieweltdertausendabenteuer.ui.items

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.items.ItemsViewModel
import kotlinx.coroutines.Runnable

class ItemsDialog(val itemsViewModel: ItemsViewModel) {

    private fun show(
        context: Context,
        textTitle: String,
        textInput: String?,
        textHint: String?,
        textOk: String,
        persist: (String) -> Unit
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(textTitle)

        val input = EditText(context)
        if (textInput != null) {
            input.text.append(textInput)
        }
        if (textHint != null) {
            input.hint = textHint
        }
        input.inputType = InputType.TYPE_CLASS_TEXT
        dialog.setView(input)

        dialog.setPositiveButton("OK") { _, _ ->
            persist(input.text.toString())
            Toast.makeText(context, textOk, Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("Abbrechen") { _, _ ->
            Toast.makeText(context, "Abgebrochen", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

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
