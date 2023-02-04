package com.cuyer.rusthub.presentation.dashboard.servers

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Servers
import kotlinx.android.synthetic.main.server_details_recyclerview.view.*

class ServerDetailsAdapter(
    private val serversList: List<Servers>,
    private var labelsList: List<DetailsLabels>,
    private var currentPosition: Int,
    private val context: Context?): RecyclerView.Adapter<ServerDetailsAdapter.ViewHolder>() {

    private val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val label: TextView
        val value: TextView
        val modifiedLabelsList = labelsList
        init {
            label = view.label
            value = view.value
            modifiedLabelsList[0].value = serversList[currentPosition].wipe
            modifiedLabelsList[1].value = serversList[currentPosition].rating
            modifiedLabelsList[2].value = serversList[currentPosition].cycle ?: ""
            modifiedLabelsList[3].value = serversList[currentPosition].player_count
            modifiedLabelsList[4].value = serversList[currentPosition].map_name
            modifiedLabelsList[5].value = serversList[currentPosition].max_group ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.server_details_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return labelsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            label.text = labelsList[position].label
            value.text = modifiedLabelsList[position].value
        }
    }
}