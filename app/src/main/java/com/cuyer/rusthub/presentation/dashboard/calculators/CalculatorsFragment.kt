package com.cuyer.rusthub.presentation.dashboard.calculators

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.TestViewModel
import com.cuyer.rusthub.common.RecyclerItemDecoration


class CalculatorsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculatorsListAdapter
    private val viewModel by activityViewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setCurrentFragmentTag(TAG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_calculators, container, false)
        recyclerView = rootView.findViewById(R.id.CalculatorsRecyclerView)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(activity, 3)
        } else {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
        }
        adapter = CalculatorsListAdapter(CalculatorsListModel().toListModel(), context)
        recyclerView.adapter = adapter
        return rootView
    }

    companion object {
        const val TAG = "calculators_fragment"
    }
}