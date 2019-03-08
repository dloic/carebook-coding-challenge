package com.jrd.carebook.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jrd.carebook.R
import com.jrd.carebook.dagger2.MyApplication
import kotlinx.android.synthetic.main.activity_launcher.*
import javax.inject.Inject

class Launcher : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        (application as MyApplication).getAppComponent()?.inject(this)
        button_get_started.setOnClickListener { router.navigateHome() }
    }
}