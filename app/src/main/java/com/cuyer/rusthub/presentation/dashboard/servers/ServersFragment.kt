package com.cuyer.rusthub.presentation.dashboard.servers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cuyer.rusthub.R
import com.cuyer.rusthub.common.CountryMapper
import com.cuyer.rusthub.data.local.entity.ServersFiltersEntity
import com.cuyer.rusthub.domain.model.Servers
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.cuyer.rusthub.presentation.dashboard.DashboardFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_crafting.view.*
import kotlinx.android.synthetic.main.fragment_servers.*
import kotlinx.android.synthetic.main.fragment_servers.view.*

class ServersFragment : Fragment() {

    private val viewModel by activityViewModels<CoreViewModel>()
    private lateinit var adapter: ServersListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var filtersImageView: ImageView
    private lateinit var searchEditText: TextInputEditText
    private var onBackPressedCalled = false
    private var filtersList = listOf<ServersFiltersEntity>()
    private var isInitialized = false


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
        filtersImageView = rootView.ServersFilterIcon
        searchEditText = rootView.ServersSearchEdit

        swipeRefreshLayout.setOnRefreshListener {
            getServers()
            viewModel.getServersRefreshState.observe(viewLifecycleOwner) {
                when (it.isLoading) {
                    true -> showLoadingView(it.error)
                    false -> showContentView(it.servers)
                }
            }
        }

        filtersImageView.setOnClickListener {
            val serversFiltersDialog = ServersFiltersDialog()
            serversFiltersDialog.show(childFragmentManager, "servers_filters_dialog")
        }


        viewModel.serversFiltersList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                filtersList = it
                viewModel.getServersList.observe(viewLifecycleOwner) { serversList ->
                    if (serversList.isNotEmpty()) {
                        val filteredItemsList = filterServers(serversList, filtersList)
                        searchEditText.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int
                            ) {
                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                                val filteredList = filteredItemsList.filter {
                                    it.name.contains(s.toString(), ignoreCase = true)
                                }
                                viewModel.setServersSearchValue(s.toString())
                                adapter.updateList(filteredList)
                            }

                            override fun afterTextChanged(p0: Editable?) {
                            }
                        })

                        recyclerView = rootView.ServersRecyclerView
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager
                        layoutManager.scrollToPosition(viewModel.scrollPosition)

                        adapter = ServersListAdapter(serversList, context)
                        recyclerView.adapter = adapter
                        isInitialized = true

                        searchEditText.setText(viewModel.serversSearchValue.value)
                        val filteredList = filteredItemsList.filter {
                            it.name.contains(
                                viewModel.serversSearchValue.value.toString(),
                                ignoreCase = true
                            )
                        }
                        adapter.updateList(filteredList)
                    }
                }
            } else {
                viewModel.getServersList.observe(viewLifecycleOwner) { serversList ->
                    if (serversList.isNotEmpty()) {
                        searchEditText.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int
                            ) {
                            }

                            override fun onTextChanged(
                                s: CharSequence?,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                                val filteredList = serversList.filter {
                                    it.name.contains(s.toString(), ignoreCase = true)
                                }
                                viewModel.setServersSearchValue(s.toString())
                                adapter.updateList(filteredList)
                            }

                            override fun afterTextChanged(p0: Editable?) {
                            }
                        })

                        recyclerView = rootView.ServersRecyclerView
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager
                        layoutManager.scrollToPosition(viewModel.scrollPosition)

                        adapter = ServersListAdapter(serversList, context)
                        recyclerView.adapter = adapter
                        isInitialized = true

                        searchEditText.setText(viewModel.serversSearchValue.value)
                        val filteredList = serversList.filter {
                            it.name.contains(
                                viewModel.serversSearchValue.value.toString(),
                                ignoreCase = true
                            )
                        }
                        adapter.updateList(filteredList)
                    }
                }
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

    private fun showLoadingView(errorMessage: String) {
        if (errorMessage.isBlank()) {
            ServersRecyclerView.visibility = View.GONE
            ServersErrorInfo.visibility = View.GONE
        } else {
            showErrorView()
        }
    }

    private fun showContentView(serversList: List<Servers>) {
        if (serversList.isNotEmpty()) {
            if (isInitialized) {
                adapter.updateList(serversList)
                swipeRefreshLayout.isRefreshing = false
                ServersRecyclerView.visibility = View.VISIBLE
                ServersErrorInfo.visibility = View.GONE
            }
        } else {
            showErrorView()
        }
    }

    private fun showErrorView() {
        ServersErrorInfo.text = "There was error fetching servers, please check Your internet connection and try to refresh"
        ServersErrorInfo.visibility = View.VISIBLE
        swipeRefreshLayout.isRefreshing = false
    }

    private fun getServers() {
        viewModel.getServersAfterRefresh()
        viewModel.getServersFromDb()
    }

    private fun filterServers(serversList: List<Servers>, filtersList: List<ServersFiltersEntity>): List<Servers> {
        return serversList.filter { server ->
            filtersList.all { filter ->
                when (filter.type) {
                    "type" -> when (filter.value) {
                        "Any" -> true
                        "Vanilla" -> server.difficulty == "Vanilla"
                        "Softcore" -> server.difficulty == "Softcore"
                        "Modded" -> server.modded == "Yes"
                        "Official" -> server.isOfficial
                        else -> false
                    }
                    "region" -> if (filter.value == "Any Region") true
                    else CountryMapper().getContinentFromCountryCode(server.server_flag.lowercase()) == filter.value
                    "rating" -> server.rating.removeSuffix("%").toDouble()/100 >= filter.value.removeSuffix("%").toDouble()/100
                    "group" -> when (filter.value) {
                        "Any Group" -> true
                        "Solo" -> (server.max_group?.toInt() ?: 0) == 1
                        "Duo" -> (server.max_group?.toInt() ?: 0) == 2
                        "Trio" -> (server.max_group?.toInt() ?: 0) == 3
                        else -> false
                    }
                    else -> true
                }
            }
        }
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