package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raid_container_recyclerview.view.*

class RaidContainerAdapter(
    private var itemsList: List<RaidModel>,
    context: Context?,
    private var initialPosition: Int,
    private var filteredItemsInitialList: List<RaidModel>,
    private var initialSelectorPosition: Int)
    : RecyclerView.Adapter<RaidContainerAdapter.ViewHolder>(),
    RaidSpinnerAdapter.SpinnerItemSelectedListener{

    private val mContext = context
    private val selector = mutableListOf("Walls", "Doors")
    private var currentSelectorIndex = initialSelectorPosition
    private var explosivesCurrentSelectorIndex = 0;
    private var explosivesCurrentSelectedIndex = 0;
    private val spinnerAdapter = RaidSpinnerAdapter(emptyList(), mContext, this, initialPosition)

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int>
        get() = _selectedPosition

    private fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }

    private val _filteredInitialList = MutableLiveData<List<RaidModel>>()
    val filteredInitialList: LiveData<List<RaidModel>>
        get() = _filteredInitialList

    private fun setInitialFilteredList(list: List<RaidModel>) {
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
        val amountEditText: TextInputEditText
        val explosivesLeftButton: Button
        val explosivesRightButton: Button
        val explosivesImage: ImageView

        init {
            raidSpinner = view.RaidSpinner
            categoryTextView = view.RaidCategoryText
            selectedImage = view.RaidSelectedImage
            categoryRightButton = view.RaidRight
            categoryLeftButton = view.RaidLeft
            amountEditText = view.RaidEditText
            explosivesLeftButton = view.RaidExplosiveLeft
            explosivesRightButton = view.RaidExplosiveRight
            explosivesImage = view.RaidExplosiveImage

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
            var filteredItems = emptyList<RaidModel>()
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
                    item.type.contains(
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
                        item.type.contains(
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
                notifyDataSetChanged()
                spinnerAdapter.updateList(filteredItems)
            }

            categoryRightButton.setOnClickListener {
                if (currentSelectorIndex < selector.size - 1) {
                    currentSelectorIndex++
                    categoryTextView.text = selector[currentSelectorIndex]
                    filteredItems = itemsList.filter { item ->
                        item.type.contains(
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
                notifyDataSetChanged()
                spinnerAdapter.updateList(filteredItems)
            }

            // explosives
            if (explosivesCurrentSelectedIndex == 0) {
                filteredItems = itemsList.filter { item ->
                    item.type.contains(
                        selector[currentSelectorIndex].dropLast(1),
                        ignoreCase = true
                    )
                }
                setInitialFilteredList(filteredItems)
                Picasso.get()
                    .load(filteredItems[0].explosives[0].image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_missing_icon)
                    .into(explosivesImage)
            } else {
                filteredItems = itemsList.filter { item ->
                    item.type.contains(
                        selector[currentSelectorIndex].dropLast(1),
                        ignoreCase = true
                    )
                }
                setInitialFilteredList(filteredItems)
                Picasso.get()
                    .load(filteredItems[explosivesCurrentSelectedIndex].explosives[0].image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_missing_icon)
                    .into(explosivesImage)
            }
            explosivesRightButton.setOnClickListener {
                if (explosivesCurrentSelectorIndex < filteredItems[explosivesCurrentSelectedIndex].explosives.size - 1) {
                    explosivesCurrentSelectorIndex++

                    Picasso.get()
                        .load(filteredItems[explosivesCurrentSelectedIndex].explosives[explosivesCurrentSelectorIndex].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(explosivesImage)
                    explosivesLeftButton.isEnabled = true
                    explosivesLeftButton.setBackgroundColor(Color.parseColor("#F6EAE0"))

                    if (explosivesCurrentSelectorIndex == filteredItems[explosivesCurrentSelectedIndex].explosives.size -1) {
                        explosivesRightButton.isEnabled = false
                        explosivesRightButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
                    }
                }
            }

            explosivesLeftButton.setOnClickListener {
                if (explosivesCurrentSelectorIndex > 0) {
                    explosivesCurrentSelectorIndex--

                    Picasso.get()
                        .load(filteredItems[explosivesCurrentSelectedIndex].explosives[explosivesCurrentSelectorIndex].image)
                        .placeholder(R.drawable.ic_placeholder_image)
                        .error(R.drawable.ic_missing_icon)
                        .into(explosivesImage)

                    explosivesRightButton.isEnabled = true
                    explosivesRightButton.setBackgroundColor(Color.parseColor("#F6EAE0"))

                    if (explosivesCurrentSelectorIndex == 0) {
                        explosivesLeftButton.isEnabled = false
                        explosivesLeftButton.setBackgroundColor(Color.parseColor("#2B2B2B"))
                    }
                }
            }
        }
    }

    override fun onImageClick(imageUrl: String, position: Int) {
        val selectedImage: ImageView = (mContext as Activity).findViewById(R.id.RaidSelectedImage)
        setSelectedPosition(position)
        explosivesCurrentSelectedIndex = position
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .error(R.drawable.ic_missing_icon)
            .into(selectedImage)
    }
}