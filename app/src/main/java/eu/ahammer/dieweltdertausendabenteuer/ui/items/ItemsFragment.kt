package eu.ahammer.dieweltdertausendabenteuer.ui.items

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import eu.ahammer.dieweltdertausendabenteuer.databinding.FragmentItemsBinding
import eu.ahammer.dieweltdertausendabenteuer.ui.MyFragment

class ItemsFragment : MyFragment<FragmentItemsBinding, ItemsViewModel>(ItemsViewModel::class.java) {

    private lateinit var button: FloatingActionButton
    private var items: ArrayList<String> = arrayListOf()
    private lateinit var itemList: ListView
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {

            button = binding.itemAddButton
            button.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle("Gegenstand hinzufügen")

                val input = EditText(requireContext())
                input.hint = "Gegenstand eingeben"
                input.inputType = InputType.TYPE_CLASS_TEXT
                dialog.setView(input)

                dialog.setPositiveButton("OK") { _, _ ->
                    items.add(input.text.toString())
                    Toast.makeText(requireContext(), "Gegenstand hinzugefügt", Toast.LENGTH_SHORT)
                        .show()
                }
                dialog.setNegativeButton("Abbrechen") { _, _ ->
                    Toast.makeText(requireContext(), "Abgebrochen", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }

            itemList = binding.itemList
            itemAdapter =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, items)
            itemList.adapter = itemAdapter
            itemList.onItemLongClickListener =
                AdapterView.OnItemLongClickListener { _, _, pos, _ ->
                    val itemText = items[pos]
                    items.removeAt(pos)
                    itemAdapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "'$itemText' gelöscht", Toast.LENGTH_SHORT)
                        .show()
                    true
                }

            return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyContext()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentItemsBinding {
        return FragmentItemsBinding.inflate(inflater, container, false)
    }
}
