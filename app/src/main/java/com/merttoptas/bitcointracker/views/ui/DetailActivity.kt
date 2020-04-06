package com.merttoptas.bitcointracker.views.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.merttoptas.bitcointracker.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var name :String
    lateinit var description:String
    lateinit var colour:String
    lateinit var imgUrl : String
    lateinit var price:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        name = intent.getStringExtra("coinName")
        description = intent.getStringExtra("coinDescription")
        colour = intent.getStringExtra("coinColour")
        imgUrl = intent.getStringExtra("coinImg")
        price = intent.getStringExtra("coinPrice")

        tv_coin_detail_name_.text = name
        tv_coin_detail_description.text =description
        tv_coin_detail_name_.setTextColor(Color.parseColor(colour))

        Glide.with(this).load(imgUrl).into(img_coin_url)

    }
}
