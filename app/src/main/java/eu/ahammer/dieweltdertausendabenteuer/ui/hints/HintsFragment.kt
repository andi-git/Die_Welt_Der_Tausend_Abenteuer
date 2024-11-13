package eu.ahammer.dieweltdertausendabenteuer.ui.hints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import eu.ahammer.dieweltdertausendabenteuer.databinding.FragmentHintsBinding

class HintsFragment : Fragment() {

    private var _binding: FragmentHintsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val hintsViewModel = ViewModelProvider(this)[HintsViewModel::class.java]

        _binding = FragmentHintsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHints
        hintsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}