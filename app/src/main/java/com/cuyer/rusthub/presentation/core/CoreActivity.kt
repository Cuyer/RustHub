package com.cuyer.rusthub.presentation.core

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cuyer.rusthub.R
import com.cuyer.rusthub.TestViewModel
import com.cuyer.rusthub.presentation.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_core.*


@AndroidEntryPoint
class CoreActivity : AppCompatActivity() {

    private val viewModel by viewModels<TestViewModel>()
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var topBarTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
        viewModel.getItems()
        viewModel.getServers()
        dashboardFragment = DashboardFragment()
        topBarTextView = TopBarTextView
        supportFragmentManager.beginTransaction().replace(FragmentContainer.id, dashboardFragment)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        topBarTextView.text = getString(R.string.app_name)
    }
}