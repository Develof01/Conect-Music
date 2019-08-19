package com.example.conct_music.extensions

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("setImage")
fun ImageView.setImageUrl(url: String?) {

    val options = RequestOptions()
        .centerCrop()
        .error(R.drawable.ic_btn_speak_now) //TODO CAMBIAR ICONO DE LA IMAGEN EN ERROR
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    Glide.with(context)
        .load(url)
        .apply(options)
        .into(this)
}

@BindingAdapter("setImageCoil")
fun ImageView.setImageUrlCoil(url: String?) {
    this.load(url) {
        crossfade(true)
    }

}