package eu.ahammer.dieweltdertausendabenteuer.viewmodel.hints

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.ahammer.dieweltdertausendabenteuer.databinding.HintsLayoutBinding
import eu.ahammer.dieweltdertausendabenteuer.model.Hint
import eu.ahammer.dieweltdertausendabenteuer.view.hints.HintsCreateEditDialog
import eu.ahammer.dieweltdertausendabenteuer.view.hints.HintsDeleteDialog

class HintsAdapter(val context: Context, val hintViewModel: HintsViewModel, val list: List<Hint>) :
    RecyclerView.Adapter<HintsAdapter.ViewHolder>() {

    class ViewHolder(val binding: HintsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HintsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.hintName.text = list[position].text
        holder.binding.hintDeleteButton.setOnClickListener {
            val hintToDelete = list[position]
            Log.i("hint", "remove hint $hintToDelete")
            HintsDeleteDialog(hintViewModel).showDelete(
                context = context,
                hintToDelete = hintToDelete,
                afterHintDeleted = {
                    notifyItemRemoved(position)
                })
        }
        holder.binding.hintEditButton.setOnClickListener {
            val hintToChange = list[position]
            Log.i("hint", "remove hint $hintToChange")
            HintsCreateEditDialog(hintViewModel).showUpdate(
                context = context,
                hintToChange = hintToChange,
                afterHintChanged = {
                    notifyItemChanged(position)
                })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}