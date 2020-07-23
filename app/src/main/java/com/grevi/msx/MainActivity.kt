package com.grevi.msx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grevi.msx.network.ApiServices
import com.grevi.msx.network.NetworkInterceptor
import com.grevi.msx.repos.Repository
import com.grevi.msx.ui.MemberAdapter
import com.grevi.msx.ui.MemberViewModel
import com.grevi.msx.utils.Status
import com.grevi.msx.utils.ViewModelFactory
import com.grevi.msx.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var memberAdapter : MemberAdapter
    private lateinit var memberViewModel : MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkInterceptor = NetworkInterceptor(this)
        val api = ApiServices(networkInterceptor)
        val repos = Repository(api)
        val factory = ViewModelFactory(repos)

        memberViewModel = ViewModelProvider(this, factory).get(MemberViewModel::class.java)
        prepareView()
    }

    private fun prepareView() {
        memberAdapter = MemberAdapter()
        rv_member.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_member.adapter = memberAdapter
        memberViewModel.getMember().observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        memberAdapter.addItem(it)
                        memberAdapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> toast(this, null)
                Status.ERROR -> toast(this, it.msg)
                Status.UNAVAILABLE -> toast(this, it.msg)
            }
        })
    }

}