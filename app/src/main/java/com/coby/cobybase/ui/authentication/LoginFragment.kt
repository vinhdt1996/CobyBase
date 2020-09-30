package com.coby.cobybase.ui.authentication

import android.content.Intent
import android.view.View
import androidx.navigation.fragment.findNavController
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.databinding.FragmentLoginBinding
import com.coby.cobybase.ui.main.MainActivity

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                startActivity(Intent(activity, MainActivity::class.java))
            }
            R.id.tvSignup -> {
                val action = LoginFragmentDirections
                    .actionLoginToSignup()
                findNavController().navigate(action)
            }
        }
    }

    override fun initViews() {
        binding.fragment = this
    }
}