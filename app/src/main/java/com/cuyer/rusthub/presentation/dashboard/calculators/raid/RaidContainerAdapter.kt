package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var initialPosition: Int)
    : RecyclerView.Adapter<RaidContainerAdapter.ViewHolder>(),
    RaidSpinnerAdapter.SpinnerItemSelectedListener{

    private val mContext = context

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int>
        get() = _selectedPosition

    private fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val raidSpinner: RecyclerView
        val categoryTextView: TextView
        val selectedImage: ImageView

        init {
            raidSpinner = view.RaidSpinner
            categoryTextView = view.RaidCategoryText
            selectedImage = view.RaidSelectedImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.raid_container_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val layoutManager = LinearLayoutManager(mContext)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            raidSpinner.layoutManager = layoutManager
            val adapter = RaidSpinnerAdapter(itemsList, mContext, this@RaidContainerAdapter, initialPosition)
            raidSpinner.adapter = adapter

            if (initialPosition == -1) {
                // show initial image
                Picasso.get()
                    .load(itemsList[0].image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_missing_icon)
                    .into(selectedImage)
            } else {
                // show image from selected position

                Picasso.get()
                    .load(itemsList[initialPosition].image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_missing_icon)
                    .into(selectedImage)
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