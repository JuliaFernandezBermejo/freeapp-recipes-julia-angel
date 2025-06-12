package es.uji.vj1229.fortuji.newsActivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide

import es.uji.vj1229.fortuji.databinding.DialogNewsBinding

class NewsDialog : DialogFragment() {
    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var binding: DialogNewsBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewsBinding.inflate(layoutInflater, null, false)
        with(binding) {
            newsTitleTextView.text = viewModel.selectedNews?.title
            newsBodyTextView.text = viewModel.selectedNews?.body
            Glide.with(root)
                .load(viewModel.selectedNews?.image)
                .fitCenter()
                .into(binding.newsDialogImageView)
        }
        return AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
    }

}