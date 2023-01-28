package com.cuyer.rusthub.presentation.dashboard.calculators.scrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.cuyer.rusthub.presentation.dashboard.calculators.crafting.CraftingFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_scrap.view.*

class ScrapFragment : Fragment(), ScrapImageAdapter.OnImageClickListener {

    private val viewModel by activityViewModels<CoreViewModel>()
    private var onBackPressedCalled = false
    private lateinit var selectedImageView: ImageView
    private lateinit var scrapRecyclerView: RecyclerView
    private lateinit var scrapAdapter: ScrapImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItemsFromDb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_scrap, container, false)
        scrapRecyclerView = rootView.ScrapRecyclerView
        selectedImageView = rootView.ScrapSelectedImage



        viewModel.getItemsList.observe(viewLifecycleOwner) { itemsList ->
            if (itemsList.isNotEmpty()) {
                val scrapableItemsList = itemsList.filter { it.scrappedComponents[0].scrap.isNotEmpty()}

                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                scrapRecyclerView.layoutManager = layoutManager
                scrapAdapter = ScrapImageAdapter(scrapableItemsList, this)
                scrapRecyclerView.adapter = scrapAdapter

                if (viewModel.getSelectedItemPosition() != -1) {
                    val selectedImageUrl = scrapableItemsList[viewModel.getSelectedItemPosition()].image
                    scrapRecyclerView.scrollToPosition(viewModel.getSelectedItemPosition())
                    Picasso.get()
                        .load(selectedImageUrl)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImageView)
                } else {
                    val firstImageUrl = scrapableItemsList[0].image
                    scrapRecyclerView.scrollToPosition(0)
                    Picasso.get()
                        .load(firstImageUrl)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImageView)
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentFragmentName("Calculators")
            onBackPressedCalled = true
            isEnabled = false
            requireActivity().onBackPressed()
        }

        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setCurrentFragmentTag(CraftingFragment.TAG)
        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Scrap")
        }
    }

    companion object {
        const val TAG = "scrap_fragment"
    }

    override fun onImageClick(imageUrl: String, position: Int) {
        viewModel.setSelectedItemPosition(position)
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(selectedImageView)
    }
}