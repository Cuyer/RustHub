package com.cuyer.rusthub.presentation.dashboard.calculators

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuyer.rusthub.R
import com.cuyer.rusthub.presentation.core.CoreActivity
import com.cuyer.rusthub.presentation.core.CoreViewModel
import kotlinx.android.synthetic.main.activity_core.*


class CalculatorsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculatorsListAdapter
    private val viewModel by activityViewModels<CoreViewModel>()
    private var onBackPressedCalled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        viewModel.setCurrentFragmentTag(TAG)
        if(!onBackPressedCalled) {
            viewModel.setCurrentFragmentName("Calculators")
        }
    }

    companion object {
        const val TAG = "calculators_fragment"
    }
}