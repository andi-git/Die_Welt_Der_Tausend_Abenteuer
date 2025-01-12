package eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Hint
import eu.ahammer.dieweltdertausendabenteuer.model.HintDao

class HintsViewModel(application: Application) : AndroidViewModel(application) {

    val hintDao: HintDao = DWdtADatabase.get(getApplication()).hintDao()

    fun addHint(hint: Hint) {
        hintDao.insert(hint)
    }

    fun updateHint(hint: Hint) {
        hintDao.update(hint)
    }

    fun deleteHint(hint: Hint) {
        hintDao.delete(hint)
    }

    fun getHints(): LiveData<List<Hint>> = hintDao.loadAllForLiveData()
}