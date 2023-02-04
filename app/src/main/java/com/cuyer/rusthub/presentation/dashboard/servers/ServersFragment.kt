package com.cuyer.rusthub.presentation.dashboard.servers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.cuyer.rusthub.presentation.dashboard.DashboardFragment
import com.cuyer.rusthub.presentation.dashboard.DashboardListAdapter
import com.cuyer.rusthub.presentation.dashboard.DashboardListModel
import com.cuyer.rusthub.presentation.dashboard.calculators.crafting.CraftingFragment
import com.cuyer.rusthub.presentation.dashboard.toListModel
import kotlinx.android.synthetic.main.fragment_servers.view.*

class ServersFragment : Fragment() {

    private val viewModel by activityViewModels<CoreViewModel>()
    private lateinit var adapter: ServersListAdapter
    private lateinit var recyclerView: RecyclerView
    private var onBackPressedCalled = false
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getServersFromDb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_servers, container, false)
        swipeRefreshLayout = rootView.SwipeToRefresh

        viewModel.getServersList.observe(viewLifecycleOwner) { serversList ->
            if (serversList.isNotEmpty()) {
                recyclerView = rootView.ServersRecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity)
                adapter = ServersListAdapter(serversList, context)
                recyclerView.adapter = adapter
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentFragmentName(getString(R.string.app_name))
            onBackPressedCalled = true
            isEnabled = false
            requireActivity().onBackPressed()
        }

        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setCurrentFragmentTag(DashboardFragment.TAG)
        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Servers")
        }
    }

    companion object {
        const val TAG = "servers_fragment"
    }
}