package com.coby.cobybase.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.databinding.FragmentSignupBinding
import com.coby.cobybase.ext.gone

class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_signup

    override val toolbarLayoutId: Int
        get() = R.layout.layout_toolbar

    override fun toolbarFunc(curActivity: AppCompatActivity?, toolbar: Toolbar?) {
        toolbar?.findViewById<AppCompatTextView>(R.id.tvTitle)?.text = "Signup"
        toolbar?.findViewById<AppCompatImageButton>(R.id.btnBack)?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}