package com.example.conct_music.view.intro

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.conct_music.R
import com.example.conct_music.databinding.ActivityIntroBinding
import com.example.conct_music.view.login.LoginActivity

class IntroActivity : AppCompatActivity(), IntroInterface {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
    }

    override fun onResume() {
        super.onResume()
        startAmination()
    }

    override fun startAmination() {
        binding.ivLogo.alpha = 0.0f
        binding.ivLogo.animate()
            .alpha(1.0f)
            .setDuration(2000)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
                    this@IntroActivity.finish()
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })
    }
}
