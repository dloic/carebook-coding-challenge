package com.jrd.carebook.activities

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jrd.carebook.R
import com.jrd.carebook.model.Patient
import com.jrd.carebook.model.State
import com.jrd.carebook.ui.PatientsAdapter
import com.jrd.carebook.ui.PatientsViewModel
import kotlinx.android.synthetic.main.activity_home.*

class Home : BaseActivity() {

    private lateinit var patientsViewModel: PatientsViewModel
    private var patientsAdapter: PatientsAdapter = PatientsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        patientsViewModel = PatientsViewModel(application)
        patientsViewModel.addPatients()
        initAdapter()
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recycler_patients.layoutManager = linearLayoutManager
        recycler_patients.adapter = patientsAdapter
        recycler_patients.itemAnimator = DefaultItemAnimator()

        /**
         * Observe the live data of the patients list and of the state of the loading mechanism update the UI accordingly
         */
        patientsViewModel.patientList.observe(this, Observer<PagedList<Patient>> {
            patientsAdapter.submitList(it)
        })

        patientsViewModel.state.observe(this, Observer<State> {
            if (it != null) {
                when (it) {
                    State.LOADING -> progress_bar_home.visibility = View.VISIBLE
                    State.LOADED -> progress_bar_home.visibility = View.GONE
                    State.ERROR -> displayRetrySnackbar()
                }
            }
        })
    }

    private fun displayRetrySnackbar() {
        progress_bar_home.visibility = View.GONE
        Snackbar.make(layout_home, R.string.failed_to_fetch, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry) {
            patientsViewModel.retry.invoke()
        }.show()
    }
}