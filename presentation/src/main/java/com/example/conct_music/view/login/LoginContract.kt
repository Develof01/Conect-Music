package com.example.conct_music.view.login

interface LoginContract {

    interface View {
        fun startActivity(_class: Class<*>)
        fun initDependences()
    }

}