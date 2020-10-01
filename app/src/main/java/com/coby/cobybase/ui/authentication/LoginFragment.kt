package com.coby.cobybase.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.coby.cobybase.MainApplication
import com.coby.cobybase.R
import com.coby.cobybase.base.BaseActivity
import com.coby.cobybase.base.BaseFragment
import com.coby.cobybase.constant.AppConst
import com.coby.cobybase.constant.AppConst.DEFAULT_EMAIL
import com.coby.cobybase.constant.AppConst.DEFAULT_FCM_TOKEN
import com.coby.cobybase.constant.AppConst.DEFAULT_PASSWORD
import com.coby.cobybase.databinding.FragmentLoginBinding
import com.coby.cobybase.ext.removeToolbar
import com.coby.cobybase.param.LoginParam
import com.coby.cobybase.ui.main.MainActivity
import com.coby.cobybase.utils.PopupUtil
import com.coby.cobybase.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                viewModel.login(LoginParam(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_FCM_TOKEN))
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
        removeToolbar()
    }

    override fun initObservers() {
        viewModel.loginLiveData.observe(this, Observer {
            MainApplication.instance.saveUser(it)
            startActivity(Intent(activity, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            activity?.finish()
        })

        viewModel.errorLiveData.observe(this, Observer {
            PopupUtil.showPopupError(it.first)
        })
    }
}