package es.uji.vj1229.fortuji.searchActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.vj1229.fortuji.common.Cosmetic
import es.uji.vj1229.fortuji.databinding.ItemSearchBinding

class SearchAdapter(private val cosmetics: List<Cosmetic>, private val onClickListener: (Cosmetic) -> Unit):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemSearchBinding):
        RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cosmetics[position].let { entry ->
            with(holder.binding) {
                nameTextView.text = entry.name
                descriptionTextView.text = entry.description
                root.setOnClickListener{
                    onClickListener(entry)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return cosmetics.size
    }
}