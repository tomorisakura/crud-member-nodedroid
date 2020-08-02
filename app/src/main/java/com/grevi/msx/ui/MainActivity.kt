package com.grevi.msx.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grevi.msx.R
import com.grevi.msx.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    private lateinit var memberAdapter : MemberAdapter
    private lateinit var memberViewModel : MemberViewModel

    override val di by di()

    private val factory : ViewModelFactory by instance<ViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memberViewModel = ViewModelProvider(this, factory).get(MemberViewModel::class.java)
        prepareView()
        addItem()
    }

    private fun prepareView() {
        memberAdapter = MemberAdapter()
        rv_member.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_member.adapter = memberAdapter
        try {
            memberViewModel.getMember().observe(this, Observer {
                when(it.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            memberAdapter.addItem(it.result)
                            memberAdapter.notifyDataSetChanged()
                        }
                    }
                    Status.LOADING -> toast(this, null)
                    Status.ERROR -> toast(this, it.msg)
                }
            })
        } catch (e : NoConnectionException) {
            toast(this, "No Connection")
        }
    }

    private fun addItem() {
        btn_add.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }

}