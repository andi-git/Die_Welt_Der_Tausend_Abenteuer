package eu.ahammer.dieweltdertausendabenteuer.view.hints

import android.content.Context
import android.util.Log
import eu.ahammer.dieweltdertausendabenteuer.model.Hint
import eu.ahammer.dieweltdertausendabenteuer.view.YesNoDialog
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints.HintsViewModel
import kotlinx.coroutines.Runnable

class HintsDeleteDialog(val hintsViewModel: HintsViewModel) : YesNoDialog() {

    fun showDelete(context: Context, hintToDelete: Hint, afterHintDeleted: Runnable) {
        show(
            context = context,
            textTitle = "Hinweis '${hintToDelete.text}' löschen?",
            textOk = "Hinweis ${hintToDelete.text} gelöscht",
            textButtonOk = "Hinweis löschen",
            perform = {
                Log.i("item", "delete item $hintToDelete")
                hintsViewModel.deleteHint(hintToDelete)
                afterHintDeleted.run()
            }
        )
    }
}
