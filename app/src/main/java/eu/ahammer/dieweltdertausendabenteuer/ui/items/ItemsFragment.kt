package eu.ahammer.dieweltdertausendabenteuer.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import eu.ahammer.dieweltdertausendabenteuer.databinding.FragmentItemsBinding
import eu.ahammer.dieweltdertausendabenteuer.ui.MyFragment

class ItemsFragment : MyFragment<FragmentItemsBinding, ItemsViewModel>(ItemsViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {
            val textView: TextView = binding.textItems
            getViewModel().text.observe(viewLifecycleOwner) {
                textView.text = it
            }
            return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyContext()
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentItemsBinding {
        return FragmentItemsBinding.inflate(inflater, container, false)
    }
}

