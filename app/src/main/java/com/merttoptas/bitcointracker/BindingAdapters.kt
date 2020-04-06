package com.merttoptas.bitcointracker

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        GlideToVectorYou
            .init()
            .with(imgView.context)
            .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)
            .load(imgUri, imgView)
    }
}

@BindingAdapter("colour")
fun bindColor(textView: TextView, textColor: String?) {
    textColor?.let{
        val color: Int = try {
            Color.parseColor(textColor)
        } catch (e: Exception) {
            Color.parseColor("#$000000")
        }
        textView.setTextColor(color)
    }


}
