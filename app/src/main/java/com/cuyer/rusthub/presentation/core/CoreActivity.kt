package com.cuyer.rusthub.presentation.core

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cuyer.rusthub.R
import com.cuyer.rusthub.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_core.*


@AndroidEntryPoint
class CoreActivity : AppCompatActivity() {

    private val viewModel by viewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
        viewModel.getItems()
        viewModel.getServers()
    }
}