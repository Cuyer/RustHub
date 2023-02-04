package com.cuyer.rusthub.presentation.dashboard.servers

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ContentInfoCompat.Flags
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Servers
import kotlinx.android.synthetic.main.labels_list_recyclerview.view.*

class LabelsAdapter(private val flagName: String,
                    private val labelsList: List<Labels>,
                    private val context: Context?): RecyclerView.Adapter<LabelsAdapter.ViewHolder>(){

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val officialText: TextView
        val moddedText: TextView
        val difficultyText: TextView
        val scheduleText: TextView
        val officialDivider: View
        val moddedDivider: View
        val difficultyDivider: View
        val scheduleDivider: View
        val flag: ImageView

        init {
            officialText = view.OfficialLabelText
            moddedText = view.ModdedLabelText
            difficultyText = view.DifficultyLabelText
            scheduleText = view.ScheduleLabelText

            officialDivider = view.OfficialDivider
            moddedDivider = view.ModdedDivider
            difficultyDivider = view.DifficultyDivider
            scheduleDivider = view.ScheduleDivider

            flag = view.ServerFlag
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LabelsAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.labels_list_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LabelsAdapter.ViewHolder, position: Int) {
        with(holder) {
            val drawableId = mContext!!.resources.getIdentifier("com.cuyer.rusthub:drawable/$flagName", null, null)
            if (drawableId != 0 ) {
                flag.setImageDrawable(ContextCompat.getDrawable(mContext, drawableId))
            }
            val labels = labelsList[position]
            if (labels.officialLabel.isNotEmpty()) {
                officialText.text = labels.officialLabel
                officialText.visibility = View.VISIBLE
                officialDivider.visibility = View.VISIBLE
            }
            if (labels.moddedLabel == "Yes") {
                moddedText.text = "Modded"
                moddedText.visibility = View.VISIBLE
                moddedDivider.visibility = View.VISIBLE
            }
            if (labels.difficultyLabel.isNotEmpty()) {
                difficultyText.text = labels.difficultyLabel
                difficultyText.visibility = View.VISIBLE
                difficultyDivider.visibility = View.VISIBLE
            }
            if (labels.wipeScheduleLabel.isNotEmpty()) {
                scheduleText.text = labels.wipeScheduleLabel
                scheduleText.visibility = View.VISIBLE
                scheduleDivider.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return labelsList.size
    }

}