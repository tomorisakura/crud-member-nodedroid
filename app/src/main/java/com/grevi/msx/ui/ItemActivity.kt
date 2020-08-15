package com.grevi.msx.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.grevi.msx.R
import com.grevi.msx.model.Member
import com.grevi.msx.utils.toast
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    companion object {
        const val member = "member"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        prepareView()
    }

    private fun prepareView() {
        val item = intent?.getParcelableExtra<Member>(member)
        name.text = item?.name
        age.text = item?.age.toString()
        address.text = item?.address
        hobby.text = item?.hobby

        btn_update.setOnClickListener {
            val mView = layoutInflater.inflate(R.layout.item_dialog, null)
            val mName = mView.findViewById<TextInputLayout>(R.id.u_name)
            val mAge = mView.findViewById<TextInputLayout>(R.id.u_age)
            val mAddress = mView.findViewById<TextInputLayout>(R.id.u_address)
            val mHobby = mView.findViewById<TextInputLayout>(R.id.u_hobby)

            MaterialAlertDialogBuilder(this)
                .setView(mView)
                .setTitle("Update ${item?.name} ?")
                .setNegativeButton("Cancel"){dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .setPositiveButton("Update"){ _, i ->

                }
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mn_delete -> toast(this, "Deleted")
        }
        return super.onOptionsItemSelected(item)
    }

}