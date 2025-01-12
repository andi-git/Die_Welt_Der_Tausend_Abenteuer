package eu.ahammer.dieweltdertausendabenteuer.view

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

abstract class YesNoDialog {

    protected fun show(
        context: Context,
        textTitle: String,
        textOk: String,
        textButtonOk: String,
        perform: () -> Unit
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(textTitle)

        dialog.setPositiveButton(textButtonOk) { _, _ ->
            perform()
            Toast.makeText(context, textOk, Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("Abbrechen") { _, _ ->
            Toast.makeText(context, "Abgebrochen", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}