package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Items
import com.cuyer.rusthub.presentation.dashboard.calculators.scrap.ScrapImageAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raid_container_recyclerview.*
import kotlinx.android.synthetic.main.raid_container_recyclerview.view.*
import kotlinx.android.synthetic.main.raid_spinner_item.view.*
import okhttp3.internal.notify

class RaidSpinnerAdapter(
    private var itemsList: List<RaidModel>,
    context: Context?,
    private val listener: SpinnerItemSelectedListener?,
    private val scrollPosition: Int
): RecyclerView.Adapter<RaidSpinnerAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView

        init {
            image = view.RaidSpinnerImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.raid_spinner_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            Picasso.get()
                .load(itemsList[position].image)
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_missing_icon)
                .into(image)

            holder.view.setOnClickListener{
                listener?.onImageClick(itemsList[position].image, position)
            }
        }
    }

    interface SpinnerItemSelectedListener {
        fun onImageClick(imageUrl: String, position: Int)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.scrollToPositionWithOffset(scrollPosition, 0)
            layoutManager.stackFromEnd = false
            layoutManager.isSmoothScrollbarEnabled = true
            layoutManager.recycleChildrenOnDetach = true
            recyclerView.setHasFixedSize(true)
        }
    }

    fun updateList(newList: List<RaidModel>) {
        itemsList = newList
        notifyDataSetChanged()
    }

}