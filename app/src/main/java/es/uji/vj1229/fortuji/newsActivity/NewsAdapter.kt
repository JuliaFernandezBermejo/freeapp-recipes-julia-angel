package es.uji.vj1229.fortuji.newsActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.uji.vj1229.fortuji.common.News
import es.uji.vj1229.fortuji.databinding.ItemNewsBinding

class NewsAdapter(private val news: List<News>, private val onClickListener: (News) -> Unit):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news[position].let { entry ->
            with(holder.binding) {
                itemTextView.text = entry.title
                root.setOnClickListener{
                    onClickListener(entry)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}