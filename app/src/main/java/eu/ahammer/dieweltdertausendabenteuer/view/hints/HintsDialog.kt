package eu.ahammer.dieweltdertausendabenteuer.view.hints

import android.content.Context
import android.util.Log
import eu.ahammer.dieweltdertausendabenteuer.model.Hint
import eu.ahammer.dieweltdertausendabenteuer.view.InputFieldDialog
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints.HintsViewModel
import kotlinx.coroutines.Runnable

class HintsDialog(val hintsViewModel: HintsViewModel) : InputFieldDialog() {

    fun showInsert(context: Context) {
        show(
            context = context,
            textTitle = "Gegenstand hinzufügen",
            textInput = null,
            textHint = "Gegenstand eingeben",
            textOk = "Gegenstand hinzugefügt",
            persist = { hintText ->
                Log.i("hint", "insert hint $hintText")
                hintsViewModel.addHint(Hint(text = hintText))
            }
        )
    }

    fun showUpdate(context: Context, hintToChange: Hint, afterHintChanged: Runnable) {
        show(
            context = context,
            textTitle = "Hinweis ändern",
            textInput = hintToChange.text,
            textHint = null,
            textOk = "Hinweis geändert",
            persist = { hintText ->
                Log.i("hint", "update hint from $hintToChange")
                hintToChange.text = hintText.toString()
                Log.i("hint", "update hint to $hintToChange")
                hintsViewModel.updateHint(hintToChange)
                afterHintChanged.run()
            }
        )
    }
}
