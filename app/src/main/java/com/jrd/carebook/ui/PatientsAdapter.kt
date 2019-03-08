package com.jrd.carebook.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jrd.carebook.R
import com.jrd.carebook.model.Patient

class PatientsAdapter : PagedListAdapter<Patient, PatientsViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        return PatientsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bundle_response,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val patient = getItem(position)
        patient?.let { holder.bind(it) }
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Patient>() {
            override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean =
                oldItem.id == newItem.id
        }
    }
}