package eu.ahammer.dieweltdertausendabenteuer.view.fight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import eu.ahammer.dieweltdertausendabenteuer.databinding.FightFragmentBinding
import eu.ahammer.dieweltdertausendabenteuer.view.MyFragment

class FightFragment : MyFragment<FightFragmentBinding, FightViewModel>(FightViewModel::class.java) {

    val fightTable: Map<Int, Map<Char, FightResult>> =
        mapOf(
            Pair(
                1, mapOf(
                    Pair('A', FightResult.WIN),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.LOSS),
                    Pair('D', FightResult.DRAW),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                2, mapOf(
                    Pair('A', FightResult.DRAW),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.LOSS),
                    Pair('D', FightResult.WIN),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                3, mapOf(
                    Pair('A', FightResult.LOSS),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.WIN),
                    Pair('D', FightResult.WIN),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                4, mapOf(
                    Pair('A', FightResult.DRAW),
                    Pair('B', FightResult.LOSS),
                    Pair('C', FightResult.LOSS),
                    Pair('D', FightResult.DRAW),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                5, mapOf(
                    Pair('A', FightResult.DRAW),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.WIN),
                    Pair('D', FightResult.LOSS),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                6, mapOf(
                    Pair('A', FightResult.DRAW),
                    Pair('B', FightResult.WIN),
                    Pair('C', FightResult.DRAW),
                    Pair('D', FightResult.DRAW),
                    Pair('E', FightResult.LOSS)
                )
            ),
            Pair(
                7, mapOf(
                    Pair('A', FightResult.DRAW),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.LOSS),
                    Pair('D', FightResult.LOSS),
                    Pair('E', FightResult.DRAW)
                )
            ),
            Pair(
                8, mapOf(
                    Pair('A', FightResult.LOSS),
                    Pair('B', FightResult.DRAW),
                    Pair('C', FightResult.LOSS),
                    Pair('D', FightResult.DRAW),
                    Pair('E', FightResult.WIN)
                )
            ),
        )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        withinContext(inflater, container) {
            val fightButtonsNumber = arrayListOf<Button>(
                binding.fightButton1,
                binding.fightButton2,
                binding.fightButton3,
                binding.fightButton4,
                binding.fightButton5,
                binding.fightButton6,
                binding.fightButton7,
                binding.fightButton8
            )
            val fightButtonsCharacter = arrayListOf<Button>(
                binding.fightButtonA,
                binding.fightButtonB,
                binding.fightButtonC,
                binding.fightButtonD,
                binding.fightButtonE
            )

            fightButtonsNumber.forEach { button ->
                button.setOnClickListener {
                    fightButtonsNumber.forEach { otherButton ->
                        if (otherButton.id != button.id) {
                            otherButton.deSelect()
                        }
                    }
                    if (button.isSelected) {
                        button.deSelect()
                    } else {
                        button.select()
                    }
                }
            }
            fightButtonsCharacter.forEach { button ->
                button.setOnClickListener {
                    fightButtonsCharacter.forEach { otherButton ->
                        if (otherButton.id != button.id) {
                            otherButton.deSelect()
                        }
                    }
                    if (button.isSelected) {
                        button.deSelect()
                    } else {
                        button.select()
                        binding.fightResult.text = "..."
                    }
                }
            }
            binding.fightButtonFight.setOnClickListener {
                val buttonNumber: Button? = fightButtonsNumber.selected()
                val buttonCharacter: Button? = fightButtonsCharacter.selected()
                if (buttonNumber != null && buttonCharacter != null) {
                    val number: Int = buttonNumber.text.toString().toInt()
                    val character: Char = buttonCharacter.text.toString()[0]
                    binding.fightResult.text = fightTable[number]?.get(character).toString()
                    fightButtonsNumber.forEach { it.deSelect() }
                    fightButtonsCharacter.forEach { it.deSelect() }
                }
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
    ): FightFragmentBinding {
        return FightFragmentBinding.inflate(inflater, container, false)
    }
}

fun Button.select() {
    this.isSelected = true
    this.setBackgroundColor(0xFFD2B48C.toInt())
    this.setTextColor(0xFF000000.toInt())
}

fun Button.deSelect() {
    this.isSelected = false
    this.setBackgroundColor(0xFF1F3C88.toInt())
    this.setTextColor(0xFFFFFFFF.toInt())
}

fun List<Button>.selected(): Button? {
    return this.firstOrNull { it.isSelected }
}

enum class FightResult {
    WIN,
    LOSS,
    DRAW
}