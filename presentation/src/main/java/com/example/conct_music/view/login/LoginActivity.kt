package com.example.conct_music.view.login

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.conct_music.R
import com.example.conct_music.databinding.ActivityLoginBinding
import com.example.conct_music.view.dashboard.DashboardActivity
import com.example.conct_music.view.signin.SignInActivity
import com.example.domian.entities.User
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import com.example.conct_music.extensions.displayMessage
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val isPresenterLoad = getKoin().getAll<LoginViewModel>()
    private val isUserLoad = getKoin().getAll<User>()
    private val loadFeatures by lazy { loadKoinModules(LoginModule.getLoginModule()) }

    private val loginViewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        if (isPresenterLoad.isEmpty()) initDependences()

        binding.loginActivity = this
        binding.viewModel = loginViewModel

        loginViewModel.userError.observe(this, Observer<String> {
            binding.tilUser.error = it
        })

        loginViewModel.pwdError.observe(this, Observer<String> {
            binding.tilPwd.error = it
        })

        loginViewModel.userResponse?.observe(this, Observer<User> {
            it?.let {
                if (isUserLoad.isEmpty())loadKoinModules(LoginModule.loadUserModule(it))
                startActivity(DashboardActivity::class.java)
            } ?:
            this.displayMessage(resources.getString(R.string.login_error), resources.getString(R.string.user_not_found))
        })
    }

    fun registerUser() = startActivity(SignInActivity::class.java)

    override fun startActivity(_class: Class<*>) {
        startActivity(Intent(this@LoginActivity, _class))
    }

    override fun initDependences() = loadFeatures

}
