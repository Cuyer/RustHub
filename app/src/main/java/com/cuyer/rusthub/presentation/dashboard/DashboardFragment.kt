package com.cuyer.rusthub.presentation.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.TestViewModel

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DashboardListAdapter
    private val viewModel by activityViewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = rootView.findViewById(R.id.DashboardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = DashboardListAdapter(DashboardListModel().toListModel(), context)
        recyclerView.adapter = adapter
        return rootView
    }

    companion object {
        const val TAG = "dashboard_fragment"
    }
}