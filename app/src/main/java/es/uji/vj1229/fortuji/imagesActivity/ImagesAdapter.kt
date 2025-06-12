package es.uji.vj1229.fortuji.imagesActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.uji.vj1229.fortuji.databinding.ItemImagesBinding

class ImagesAdapter (private val images: List<Pair<String, String>>):
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

        class ViewHolder(val binding: ItemImagesBinding) :
                RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImagesBinding.inflate(
            inflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        images[position].let { entry ->
            with(holder.binding) {
                imageNameTextView.text = entry.first
                Glide.with(root)
                    .load(entry.second)
                    .centerInside()
                    .into(cosmeticsImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}