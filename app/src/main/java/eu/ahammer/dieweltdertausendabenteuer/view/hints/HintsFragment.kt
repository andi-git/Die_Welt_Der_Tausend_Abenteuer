package eu.ahammer.dieweltdertausendabenteuer.view.hints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import eu.ahammer.dieweltdertausendabenteuer.databinding.HintsFragmentBinding
import eu.ahammer.dieweltdertausendabenteuer.view.MyFragment
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints.HintsAdapter
import eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints.HintsViewModel

class HintsFragment : MyFragment<HintsFragmentBinding, HintsViewModel>(HintsViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {
            getViewModel().getHints().observe(this.viewLifecycleOwner, Observer { list ->
                binding.hintRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.hintRecyclerView.adapter =
                    HintsAdapter(requireContext(), getViewModel(), list)
            })
            binding.hintAddButton.setOnClickListener {
                HintsDialog(getViewModel()).showInsert(requireContext())
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
    ): HintsFragmentBinding {
        return HintsFragmentBinding.inflate(inflater, container, false)
    }
}
