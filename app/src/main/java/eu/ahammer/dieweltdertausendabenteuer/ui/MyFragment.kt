package eu.ahammer.dieweltdertausendabenteuer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class MyFragment<BINDING : ViewBinding, VIEWMODEL : ViewModel>
    (private val viewModelClass: Class<VIEWMODEL>) : Fragment() {

    protected var _binding: BINDING? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = _binding!!

    protected inline fun <R> withinContext(
        inflater: LayoutInflater,
        container: ViewGroup?,
        runWithinContext: () -> R
    ): R {
        _binding = inflateBinding(inflater, container)
        return runWithinContext()
    }

    protected fun destroyContext() {
        _binding = null
    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING;

    fun getViewModel(): VIEWMODEL {
        return ViewModelProvider(this)[viewModelClass]
    }
}
