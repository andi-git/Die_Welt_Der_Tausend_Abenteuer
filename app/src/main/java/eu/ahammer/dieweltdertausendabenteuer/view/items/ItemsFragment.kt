package eu.ahammer.dieweltdertausendabenteuer.view.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import eu.ahammer.dieweltdertausendabenteuer.databinding.ItemsFragmentBinding
import eu.ahammer.dieweltdertausendabenteuer.view.MyFragment
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.items.ItemsAdapter
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.items.ItemsViewModel

class ItemsFragment : MyFragment<ItemsFragmentBinding, ItemsViewModel>(ItemsViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {
            getViewModel().getItems().observe(this.viewLifecycleOwner, Observer { list ->
                binding.itemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.itemRecyclerView.adapter =
                    ItemsAdapter(requireContext(), getViewModel(), list)
            })
            binding.itemAddButton.setOnClickListener {
                ItemsDialog(getViewModel()).showInsert(requireContext())
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
    ): ItemsFragmentBinding {
        return ItemsFragmentBinding.inflate(inflater, container, false)
    }
}
