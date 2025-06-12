package es.uji.vj1229.fortuji.searchActivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import es.uji.vj1229.fortuji.databinding.DialogSearchBinding

class SearchDialog : DialogFragment() {
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var binding: DialogSearchBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSearchBinding.inflate(layoutInflater, null, false)
        with(binding) {
            idTextView.text = viewModel.selectedCosmetic?.id
            nameTextView.text = viewModel.selectedCosmetic?.name
            typeTextView.text = viewModel.selectedCosmetic?.type
            rarityTextView.text = viewModel.selectedCosmetic?.rarity
            addedTextView.text = viewModel.selectedCosmetic?.added
            descriptionTextView.text = viewModel.selectedCosmetic?.description
            imageImageView.visibility = View.GONE
            imagesButton.isEnabled = false
            videosButton.visibility = View.GONE

            if (viewModel.selectedCosmetic?.images != null) {
                imagesButton.isEnabled = true
                imagesButton.setOnClickListener { viewModel.onShowImagesRequested() }
                imageImageView.visibility = View.VISIBLE

                Glide.with(root)
                    .load(viewModel.selectedCosmetic?.images?.get("Icon"))
                    .fitCenter()
                    .into(binding.imageImageView)
            }

            if (viewModel.selectedCosmetic?.video != null){
                videosButton.visibility = View.VISIBLE
                videosButton.setOnClickListener { viewModel.onStartVideoRequested() }
            }

        }
        return AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
    }
}