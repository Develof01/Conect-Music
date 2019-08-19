package com.example.conct_music.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.conct_music.R
import com.example.conct_music.databinding.ActivitySignInBinding
import com.example.conct_music.extensions.displayMessage
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SignInActivity : AppCompatActivity(), SignInContract.View {

    private val isViewModel = getKoin().getAll<SignInViewModel>()
    private val loadModules by lazy { loadKoinModules(SignInModule.getSignInModule()) }

    private val signInViewModel: SignInViewModel by viewModel()

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        if (isViewModel.isEmpty()) initDependences()

        binding.signin = this
        binding.viewModel = signInViewModel


        signInViewModel.userError.observe(this@SignInActivity, Observer<String> {
            binding.tilUser.error = it
        })

        signInViewModel.pwdError.observe(this@SignInActivity, Observer<String> {
            binding.tilPwd.error = it
        })

        signInViewModel.emailError.observe(this@SignInActivity, Observer<String> {
            binding.tilEmail.error = it
        })

        signInViewModel.isUserCreated.observe(this@SignInActivity, Observer<Boolean> {
            if(it) {
                Toast.makeText(this@SignInActivity, resources.getString(R.string.signin_message_user_created), Toast.LENGTH_SHORT).show()
                this@SignInActivity.finish()
            } else {
                this.displayMessage(resources.getString(R.string.signin_message), resources.getString(R.string.signin_message_user_error))
            }
        })

    }

    override fun startActivity(_class: Class<*>) {
        startActivity(Intent(this@SignInActivity, _class))
    }

    override fun initDependences() = loadModules
}
