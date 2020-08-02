package com.grevi.msx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.grevi.msx.R
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setPost()
    }

    private fun setPost() {
        var name  = p_name.editText?.text
        var age = p_age.editText?.text
        var address = p_address.editText?.text
        var hobby = p_hobby.editText?.text

        ed_age.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable) {
                if (p0.length > p_age.counterMaxLength) {
                    p_age.error ="Umur not valid"
                } else {
                    p_age.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })


        btn_post.setOnClickListener {

            if (name.isNullOrBlank() && age.isNullOrBlank()) {
                toast("Field Name and Age must be not empty !")
            } else if (address.isNullOrBlank() && hobby.isNullOrBlank()) {
                toast("Field Address and Hobby must be not empty !")
            } else {
                toast("Posting")
            }

        }
    }

    private fun toast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}