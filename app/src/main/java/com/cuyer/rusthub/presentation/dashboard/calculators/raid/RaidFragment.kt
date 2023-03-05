package com.cuyer.rusthub.presentation.dashboard.calculators.raid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreViewModel
import com.cuyer.rusthub.presentation.dashboard.calculators.CalculatorsFragment
import com.cuyer.rusthub.presentation.dashboard.calculators.scrap.ScrapImageAdapter
import kotlinx.android.synthetic.main.fragment_raid.view.*

class RaidFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var raidAdapter: RaidContainerAdapter
    private var onBackPressedCalled = false
    private val viewModel by activityViewModels<CoreViewModel>()
    private val raidList = RaidModel().create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getItemsFromDb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_raid, container, false)
        recyclerView = rootView.RaidViewContainer

                raidAdapter = RaidContainerAdapter(raidList, context, viewModel.getRaidSelectedItemPosition(),
                    viewModel.getRaidInitialFilteredList(),
                    viewModel.getSelectorPosition(),)
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = raidAdapter

                raidAdapter.selectedPosition.observe(viewLifecycleOwner) { position ->
                    viewModel.setRaidSelectedItemPosition(position)
                }

                raidAdapter.filteredInitialList.observe(viewLifecycleOwner) {list ->
                    viewModel.setRaidInitialFilteredList(list)
                }

                raidAdapter.selectorPosition.observe(viewLifecycleOwner) {selectorPosition ->
                    viewModel.setSelectorPosition(selectorPosition)
                }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.setCurrentFragmentName("Calculators")
            onBackPressedCalled = true
            isEnabled = false
            requireActivity().onBackPressed()
        }
        return rootView
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.setCurrentFragmentTag(TAG)
        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Raid")
        }
    }

    companion object {
        const val TAG = "raid_fragment"
    }
}