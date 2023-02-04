package com.cuyer.rusthub.presentation.dashboard.servers

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.domain.model.Servers
import kotlinx.android.synthetic.main.calculators_list_recyclerview.view.*
import kotlinx.android.synthetic.main.servers_list_recyclerview.view.*

class ServersListAdapter(
    private var serversList: List<Servers>,
    private val context: Context?): RecyclerView.Adapter<ServersListAdapter.ViewHolder>() {

    private val mContext = context
    private val labelsList = DetailsLabels().toDetailsLabels()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val serverName: TextView
        val serverLabel: RecyclerView
        val serverDetails: RecyclerView
        val serverIp: TextView

        init {
            serverLabel = view.LabelsRecyclerView
            serverName = view.ServerName
            serverDetails = view.ServersDetailsRecyclerView
            serverIp = view.ServerIp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.servers_list_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return serversList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            val labelList = mutableListOf<Labels>()
            val flagName = serversList[position].server_flag.lowercase()
            val server = serversList[position]
            val labels = Labels()

            serverIp.text = "client.connect " + serversList[position].serverIp
            serverIp.setOnClickListener{
                val clipboard = mContext!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("text", serverIp.text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(mContext, "Server IP copied to clipboard", Toast.LENGTH_SHORT).show()
            }

            if (server.difficulty != null) {
                labels.difficultyLabel = server.difficulty
            }
            if (server.isOfficial) {
                labels.officialLabel = "Official"
            }
            if (server.modded != null) {
                labels.moddedLabel = server.modded
            }
            if (server.wipe_schedule != null) {
                labels.wipeScheduleLabel = server.wipe_schedule
            }
            labelList.add(labels)
            serverName.text = serversList[position].name
            serverLabel.layoutManager = LinearLayoutManager(mContext)
            serverLabel.adapter = LabelsAdapter(flagName ,labelList, mContext)
            serverDetails.layoutManager = GridLayoutManager(mContext, 3)
            serverDetails.adapter = ServerDetailsAdapter(serversList, labelsList, position, mContext)
        }
    }

    fun updateList(filteredList: List<Servers>) {
        serversList = filteredList
        notifyDataSetChanged()
    }
}