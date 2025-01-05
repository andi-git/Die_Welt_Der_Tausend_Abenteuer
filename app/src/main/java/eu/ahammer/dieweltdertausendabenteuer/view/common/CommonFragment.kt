package eu.ahammer.dieweltdertausendabenteuer.view.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import eu.ahammer.dieweltdertausendabenteuer.databinding.CommonFragmentBinding
import eu.ahammer.dieweltdertausendabenteuer.view.MyFragment
import eu.ahammer.dieweltdertausendabenteuer.R

class CommonFragment : MyFragment<CommonFragmentBinding, CommonViewModel>(CommonViewModel::class.java) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {

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
    ): CommonFragmentBinding {
        return CommonFragmentBinding.inflate(inflater, container, false)
    }
}
