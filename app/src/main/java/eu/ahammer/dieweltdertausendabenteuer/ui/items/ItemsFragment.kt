package eu.ahammer.dieweltdertausendabenteuer.ui.items

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import eu.ahammer.dieweltdertausendabenteuer.databinding.FragmentItemsBinding
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.model.ItemDao
import eu.ahammer.dieweltdertausendabenteuer.ui.MyFragment
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.item.ItemAdapter
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.item.ItemViewModel
import kotlinx.coroutines.launch

class ItemsFragment : MyFragment<FragmentItemsBinding, ItemsViewModel>(ItemsViewModel::class.java) {

//    private lateinit var button: FloatingActionButton
//    private var items: ArrayList<String> = arrayListOf()
//    private lateinit var itemList: ListView
    private lateinit var itemAdapter: ArrayAdapter<String>
    private lateinit var itemDao: ItemDao

    //val itemViewModel : ItemViewModel = eu.ahammer.dieweltdertausendabenteuer.viewmodel.item.ItemViewModel(requireContext().applicationContext as Application)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {

            val itemViewModel : ItemViewModel = eu.ahammer.dieweltdertausendabenteuer.viewmodel.item.ItemViewModel(requireContext().applicationContext as Application)

            itemViewModel.getAllContacts().observe(this.viewLifecycleOwner, Observer { list ->
                binding.itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.itemRecyclerView.adapter = ItemAdapter(requireContext(), list)
            })

            binding.itemAddButton.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle("Gegenstand hinzufügen")

                val input = EditText(requireContext())
                input.hint = "Gegenstand eingeben"
                input.inputType = InputType.TYPE_CLASS_TEXT
                dialog.setView(input)

                dialog.setPositiveButton("OK") { _, _ ->
                    val itemText = input.text.toString()
                    Log.i("item", "insert item $itemText")
                    DWdtADatabase.get(requireContext()).itemDao().insert(Item(text = itemText))
                    Toast.makeText(requireContext(), "Gegenstand hinzugefügt", Toast.LENGTH_SHORT)
                        .show()
                }
                dialog.setNegativeButton("Abbrechen") { _, _ ->
                    Toast.makeText(requireContext(), "Abgebrochen", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }

            /**
            itemList = binding.itemList
            itemAdapter =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, items)
            itemList.adapter = itemAdapter



            itemDao = DWdtADatabase.get(requireContext()).itemDao()
            items = ArrayList(itemDao.getAll().map { it.text })

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
                    lifecycleScope.launch {
                        itemDao.insert(Item(0, input.text.toString()))
                        Log.i("db", itemDao.getAll().toString())
                        itemAdapter.notifyDataSetChanged()
                    }

                    Toast.makeText(requireContext(), "Gegenstand hinzugefügt", Toast.LENGTH_SHORT)
                        .show()
                }
                dialog.setNegativeButton("Abbrechen") { _, _ ->
                    Toast.makeText(requireContext(), "Abgebrochen", Toast.LENGTH_SHORT).show()
                }
                dialog.show()
            }

            itemList.onItemLongClickListener =
                AdapterView.OnItemLongClickListener { _, _, pos, _ ->
                    val itemText = items[pos]
                    items.removeAt(pos)
                    itemAdapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "'$itemText' gelöscht", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
            */
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
