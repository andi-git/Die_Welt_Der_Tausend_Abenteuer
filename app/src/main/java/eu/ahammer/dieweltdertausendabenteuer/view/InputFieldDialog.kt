package eu.ahammer.dieweltdertausendabenteuer.view

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.widget.EditText
import android.widget.Toast

abstract class InputFieldDialog {

    protected fun show(
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
}