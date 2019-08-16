package com.example.conct_music.view.signin

interface SignInContract {

     interface View {
         fun startActivity(_class: Class<*>)
         fun initDependences()
    }

}