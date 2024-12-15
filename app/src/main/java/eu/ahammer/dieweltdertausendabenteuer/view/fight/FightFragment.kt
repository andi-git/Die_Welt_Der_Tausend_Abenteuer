package eu.ahammer.dieweltdertausendabenteuer.view.fight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import eu.ahammer.dieweltdertausendabenteuer.databinding.FragmentFightBinding
import eu.ahammer.dieweltdertausendabenteuer.view.MyFragment

class FightFragment : MyFragment<FragmentFightBinding, FightViewModel>(FightViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {
            val textView: TextView = binding.textFight
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

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFightBinding {
        return FragmentFightBinding.inflate(inflater, container, false)
    }
}

