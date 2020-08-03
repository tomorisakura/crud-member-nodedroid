package com.grevi.msx.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.grevi.msx.R
import com.grevi.msx.databinding.ActivityPostBinding
import com.grevi.msx.utils.Auth
import com.grevi.msx.utils.PostViewModelFactory
import com.grevi.msx.utils.toast
import kotlinx.android.synthetic.main.activity_post.*
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class PostActivity : AppCompatActivity(), DIAware, Auth {

    private lateinit var postViewModel: PostViewModel

    override val di by di()

    private val factory : PostViewModelFactory by instance<PostViewModelFactory>()

    private lateinit var binding : ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Post Member"
        postViewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        binding.viewModel = postViewModel
        postViewModel.listener = this
        prepareButton()
    }

    private fun prepareButton() {

        ed_age.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable) {
                if (p0.length > p_age.counterMaxLength) {
                    p_age.error ="Age is not valid"
                } else {
                    p_age.error = null
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    override fun toastTest(msg: String) {
        toast(this, msg)
    }

    override fun success() {
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult(intent, 1)
        finish()
    }

}