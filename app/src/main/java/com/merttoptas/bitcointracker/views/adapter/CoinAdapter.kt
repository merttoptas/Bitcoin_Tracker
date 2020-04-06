package com.merttoptas.bitcointracker.views.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.bitcointracker.databinding.ListItemCoinBinding
import com.merttoptas.bitcointracker.views.model.Coin
import com.merttoptas.bitcointracker.views.ui.DetailActivity

class CoinAdapter (val context: Context) : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    var data = listOf<Coin>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var totalCoins = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ) {

        val  coin :Coin  = data[position]

        holder.bind(coin, position,context)

    }


    class ViewHolder private constructor(private val binding: ListItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Coin, position: Int, context: Context) {
            binding.coin = item
            binding.position = position
            binding.executePendingBindings()
            binding.tvCoinName.setOnClickListener{
                val intent = Intent(context, DetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("coinName", item.name)
                intent.putExtra("coinDescription", item.description)
                intent.putExtra("coinPrice", item.price)
                intent.putExtra("coinColour", item.color)
                intent.putExtra("coinImg", item.iconUrl)
                context.startActivity(intent)

            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCoinBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding

                )
            }
        }
    }
}