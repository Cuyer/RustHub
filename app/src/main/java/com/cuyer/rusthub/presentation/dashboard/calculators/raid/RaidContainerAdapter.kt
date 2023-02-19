package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import android.app.Activity
import android.content.ClipData.Item
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.presentation.dashboard.calculators.scrap.ScrapAmountsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raid_container_recyclerview.view.*

class RaidContainerAdapter(
    private var itemsList: List<Items>,
    context: Context?,
    private var initialPosition: Int,
    private var filteredItemsInitialList: List<Items>,
    private var initialSelectorPosition: Int)
    : RecyclerView.Adapter<RaidContainerAdapter.ViewHolder>(),
    RaidSpinnerAdapter.SpinnerItemSelectedListener{

    private val mContext = context
    private val selector = mutableListOf("Walls", "Doors")
    private var currentSelectorIndex = initialSelectorPosition
    private val spinnerAdapter = RaidSpinnerAdapter(emptyList(), mContext, this, initialPosition)

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int>
        get() = _selectedPosition

    private fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }

    private val _filteredInitialList = MutableLiveData<List<Items>>()
    val filteredInitialList: LiveData<List<Items>>
        get() = _filteredInitialList

    private fun setInitialFilteredList(list: List<Items>) {
        _filteredInitialList.value = list
    }

    private val _selectorPosition = MutableLiveData<Int>()
    val selectorPosition: LiveData<Int>
        get() = _selectorPosition

    private fun setSelectorPosition(position: Int) {
        _selectorPosition.value = position
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val raidSpinner: RecyclerView
        val categoryTextView: TextView
        val categoryLeftButton: Button
        val categoryRightButton: Button
        val selectedImage: ImageView

        init {
            raidSpinner = view.RaidSpinner
            categoryTextView = view.RaidCategoryText
            selectedImage = view.RaidSelectedImage
            categoryRightButton = view.RaidRight
            categoryLeftButton = view.RaidLeft

            if (currentSelectorIndex == 0) {
                categoryLeftButton.isEnabled = false
                categoryLeftButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
            } else if (currentSelectorIndex == selector.size - 1) {
                categoryRightButton.isEnabled = false
                categoryRightButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.raid_container_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            categoryTextView.text = selector[currentSelectorIndex]
            var filteredItems = emptyList<Items>()
            val layoutManager = LinearLayoutManager(mContext)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            raidSpinner.layoutManager = layoutManager
            if (filteredItemsInitialList.isNotEmpty()) {
                spinnerAdapter.updateList(filteredItemsInitialList)
                if (initialPosition == -1) {
                    // show initial image
                    Picasso.get()
                        .load(filteredItemsInitialList[0].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                } else {
                    // show image from selected position
                    Picasso.get()
                        .load(filteredItemsInitialList[initialPosition].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                }
            } else {
                filteredItems = itemsList.filter { item ->
                    item.scrappedComponents[0].item.contains(
                        selector[currentSelectorIndex].dropLast(1),
                        ignoreCase = true
                    )
                }
                spinnerAdapter.updateList(filteredItems)
                if (initialPosition == -1) {
                    // show initial image
                    Picasso.get()
                        .load(filteredItems[0].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                } else {
                    // show image from selected position
                    Picasso.get()
                        .load(filteredItems[initialPosition].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                }
            }
            raidSpinner.adapter = spinnerAdapter
            categoryLeftButton.setOnClickListener {
                if (currentSelectorIndex > 0) {
                    currentSelectorIndex--
                    categoryTextView.text = selector[currentSelectorIndex]
                    filteredItems = itemsList.filter { item ->
                        item.scrappedComponents[0].item.contains(
                            selector[currentSelectorIndex].dropLast(1),
                            ignoreCase = true
                        )
                    }
                    setInitialFilteredList(filteredItems)
                    setSelectorPosition(currentSelectorIndex)
                    Picasso.get()
                        .load(filteredItems[0].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                    categoryRightButton.isEnabled = true
                    categoryRightButton.setBackgroundColor(Color.parseColor("#F6EAE0"))
                    if (currentSelectorIndex == 0) {
                        categoryLeftButton.isEnabled = false
                        categoryLeftButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
                    }
                }
                spinnerAdapter.updateList(filteredItems)
            }

            categoryRightButton.setOnClickListener {
                if (currentSelectorIndex < selector.size - 1) {
                    currentSelectorIndex++
                    categoryTextView.text = selector[currentSelectorIndex]
                    filteredItems = itemsList.filter { item ->
                        item.scrappedComponents[0].item.contains(
                            selector[currentSelectorIndex].dropLast(1),
                            ignoreCase = true
                        )
                    }
                    setInitialFilteredList(filteredItems)
                    setSelectorPosition(currentSelectorIndex)
                    Picasso.get()
                        .load(filteredItems[0].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(selectedImage)
                    categoryLeftButton.isEnabled = true
                    categoryLeftButton.setBackgroundColor(Color.parseColor("#F6EAE0"))
                    if (currentSelectorIndex == selector.size - 1) {
                        categoryRightButton.isEnabled = false
                        categoryRightButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
                    }
                }
                spinnerAdapter.updateList(filteredItems)
            }
        }
    }

    override fun onImageClick(imageUrl: String, position: Int) {
        val selectedImage: ImageView = (mContext as Activity).findViewById(R.id.RaidSelectedImage)
        setSelectedPosition(position)
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(selectedImage)
    }
}